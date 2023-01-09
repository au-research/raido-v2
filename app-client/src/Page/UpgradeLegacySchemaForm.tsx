import { useMutation } from "@tanstack/react-query";
import {
  ContributorBlock,
  ContributorIdentifierSchemeType,
  ContributorPositionSchemeType,
  ContributorRoleSchemeType,
  LegacyMetadataSchemaV1, OrganisationBlock, OrganisationIdentifierSchemeType, OrganisationRoleSchemeType,
  RaidoMetadataSchemaV1,
  ReadRaidResponseV2,
  ValidationFailure
} from "Generated/Raidv2";
import { assert } from "Util/TypeUtil";
import { CompactErrorPanel } from "Error/CompactErrorPanel";
import { Alert, Stack, TextField } from "@mui/material";
import { PrimaryActionButton, SecondaryButton } from "Component/AppButton";
import { navBrowserBack } from "Util/WindowUtil";
import { ValidationFailureDisplay } from "Component/Util";
import React, { useState } from "react";
import { useAuthApi } from "Api/AuthApi";
import {findOrcidProblem, findOrganisationIdProblem, mapInvalidOrcidChars} from "Page/MintRaidPage";

function isDifferent(formData: FormData, original: FormData){
  return formData.leadContributor !== original.leadContributor;
}

type FormData = Readonly<{
  readonly leadContributor: string,
  readonly leadOrganisation: string,
}>;
/* Going to stick with the FormData/ValidatorFormData types (from the EditForm)
because I think we'll end up needing it eventually.
*/
type ValidFormData = FormData;

function mapReadQueryDataToFormData(
  raid: ReadRaidResponseV2, 
  metadata: LegacyMetadataSchemaV1
): ValidFormData{
  return {
    leadContributor: "",
    leadOrganisation: "",
  }
}

function createUpgradeMetadata(
  formData: ValidFormData,
  oldMetadata: LegacyMetadataSchemaV1
): RaidoMetadataSchemaV1{
  return {
    ...oldMetadata,
    metadataSchema: "RaidoMetadataSchemaV1",
    contributors: [
      createLeadContributor(
        formData.leadContributor, oldMetadata.dates.startDate )],
    organisations: [
      createLeadOrganisation(formData.leadOrganisation, oldMetadata.dates.startDate)
    ],
  };
}

export function UpgradeLegacySchemaForm({onUpgradeSuccess, raid, metadata}:{
  onUpgradeSuccess: ()=>void,
  raid: ReadRaidResponseV2,
  metadata: LegacyMetadataSchemaV1,
}){
  const [serverValidations, setServerValidations] = useState(
    [] as ValidationFailure[] );
  const api = useAuthApi();
  const [formData, setFormData] = useState(
    mapReadQueryDataToFormData(raid, metadata) );

  const upgradeRequest = useMutation(
    async (props: {
      formData: ValidFormData, 
      oldMetadata: LegacyMetadataSchemaV1
    }) => {
      setServerValidations([]);
      return await api.basicRaid.upgradeLegacyToRaidoSchema({
        updateRaidoSchemaV1Request: {metadata:
            createUpgradeMetadata(props.formData, props.oldMetadata)
        }
      });
    },
    {
      onSuccess: async (mintResult) => {
        if( !mintResult.success ){
          assert(mintResult.failures);
          setServerValidations(mintResult.failures);
        }
        else {
          onUpgradeSuccess();
        }
      },
    }
  );

  const leadContribProblem = findOrcidProblem(formData.leadContributor);
  const leadOrganisationProblem = findOrganisationIdProblem(formData.leadOrganisation);
  const hasChanged =
    isDifferent(formData, mapReadQueryDataToFormData(raid, metadata));

  const canSubmit = !leadContribProblem && !leadOrganisationProblem && hasChanged;
  const isWorking = upgradeRequest.isLoading;

  return <>
    <Alert severity="warning">This is a legacy RAiD</Alert>
    <br/>
    <Alert severity="info">
      Before the the RAiD data can be edited you must "upgrade" the RAiD
      metadata by providing the data below.
    </Alert>
    <br/>
    <form autoComplete="off" onSubmit={async (e) => {
      e.preventDefault();
      await upgradeRequest.mutate({
        formData: formData as ValidFormData,
        oldMetadata: metadata
      });
    }}>
      <Stack spacing={2}>
        <TextField id="contributor"
          variant="outlined" autoCorrect="off" autoCapitalize="on"
          disabled={isWorking}
          value={formData.leadContributor ?? ""}
          onChange={(e) => {
           setFormData({
             ...formData,
             leadContributor: mapInvalidOrcidChars(e.target.value)
           });
          }}
          label={leadContribProblem ?
           "Lead contributor - " + leadContribProblem :
           "Lead contributor"}
          error={!!leadContribProblem}
        />
        <TextField id="organisation"
          variant="outlined" autoCorrect="off" autoCapitalize="on"
          disabled={isWorking}
          value={formData.leadOrganisation ?? ""}
          onChange={(e) => {
          setFormData({
            ...formData,
            leadOrganisation: e.target.value
          });
          }}
          label={leadOrganisationProblem ?
          "Lead organisation - " + leadOrganisationProblem :
          "Lead organisation"}
          error={!!leadOrganisationProblem}
        />
        <Stack direction={"row"} spacing={2}>
          <SecondaryButton type="button" onClick={(e) => {
            e.preventDefault();
            navBrowserBack();
          }}
                           disabled={isWorking}>
            Back
          </SecondaryButton>
          <PrimaryActionButton type="submit" context={"upgrading raid"}
                               disabled={!canSubmit}
                               isLoading={isWorking}
                               error={upgradeRequest.error}
          >
            Upgrade
          </PrimaryActionButton>
        </Stack>
        <CompactErrorPanel error={upgradeRequest.error}/>
        <ValidationFailureDisplay failures={serverValidations}/>
      </Stack>
    </form>
  </>;
}

export function createLeadContributor(
  leadContribOrcid: string, 
  startDate: Date
): ContributorBlock{
  return {
    id: leadContribOrcid,
    identifierSchemeUri: ContributorIdentifierSchemeType.HttpsOrcidOrg,
    positions: [{
      position: "Leader",
      positionSchemaUri: ContributorPositionSchemeType.HttpsRaidOrg,
      startDate: startDate,
    }],
    roles: [{
      role: "project-administration",
      roleSchemeUri: ContributorRoleSchemeType.HttpsCreditNisoOrg,
    }]
  }
}

export function createLeadOrganisation(
  id: string,
  startDate: Date
): OrganisationBlock{
  return {
    id,
    identifierSchemeUri: OrganisationIdentifierSchemeType.HttpsRorOrg,
    roles: [{
      role: "Lead Research Organisation",
      roleSchemeUri: OrganisationRoleSchemeType.HttpsRaidOrg,
      startDate: startDate,
    }],
  }
}
