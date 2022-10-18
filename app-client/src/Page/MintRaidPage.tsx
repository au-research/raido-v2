import {
  isPagePath,
  NavigationState,
  NavPathResult,
  NavTransition,
  parsePageSuffixParams,
  useNavigation
} from "Design/NavigationProvider";
import { raidoTitle } from "Component/Util";
import { LargeContentMain } from "Design/LayoutMain";
import { ContainerCard } from "Design/ContainerCard";
import React, { useState } from "react";
import { useMutation } from "@tanstack/react-query";
import { ApiKey, MintRaidRequestV1 } from "Generated/Raidv2";
import { useAuthApi } from "Api/AuthApi";
import { CompactErrorPanel } from "Error/CompactErrorPanel";
import { Stack, TextField } from "@mui/material";
import { PrimaryActionButton, SecondaryButton } from "Component/AppButton";
import { navBrowserBack } from "Util/WindowUtil";
import { HelpChip, HelpPopover } from "Component/HelpPopover";
import { DesktopDatePicker } from "@mui/x-date-pickers";
import { Dayjs } from "dayjs";
import { getEditRaidPageLink } from "Page/EditRaidPage";

const log = console;

const pageUrl = "/mint-raid";

export function getMintRaidPageLink(servicePointId: number): string{
  return `${pageUrl}/${servicePointId}`;
}

export function isMintRaidPagePath(pathname: string): NavPathResult{
  return isPagePath(pathname, pageUrl);
}

export function getServicePointIdFromPathname(nav: NavigationState): number{
  return parsePageSuffixParams<number>(nav, isMintRaidPagePath, Number)
}

export function MintRaidPage(){
  return <NavTransition isPagePath={isMintRaidPagePath}
    title={raidoTitle("Mint RAiD")}
  >
    <Content/>
  </NavTransition>
}


function Content(){
  const nav = useNavigation()
  const [servicePointId] = 
    useState(getServicePointIdFromPathname(nav));

  return <LargeContentMain>
    <MintRaidContainer 
      servicePointId={servicePointId}
      onCreate={(handle)=>{
        nav.replace(getEditRaidPageLink(handle));
      }}
    />
  </LargeContentMain>
}

function isDifferent(formData: ApiKey, original: ApiKey){
  return formData.subject !== original.subject ||
    formData.role !== original.role ||
    formData.enabled !== original.enabled ||
    formData.tokenCutoff?.getTime() !== original.tokenCutoff?.getTime();
}

function MintRaidContainer({servicePointId, onCreate}: {
  servicePointId: number,
  onCreate: (handle: string)=>void,
}){
  const api = useAuthApi();
  const [formData, setFormData] = useState({
    // id set to null signals creation is being requested  
    handle: undefined as unknown as string,
    servicePointId: servicePointId,
    name: "",
    startDate: new Date(),
  } as MintRaidRequestV1);
  const mintRequest = useMutation(
    async (data: MintRaidRequestV1) => {
      return await api.basicRaid.mintRaidV1({mintRaidRequestV1: data});
    },
    {
      onSuccess: async (data) => {
        onCreate(data.handle);
      },
    }
  );

  const isNameValid = !!formData.name;
  const canSubmit = isNameValid;
  const isWorking = mintRequest.isLoading;
  
  return <ContainerCard title={"Mint RAiD"} action={<MintRaidHelp/>}>
    <form autoComplete="off" onSubmit={(e) => {
      e.preventDefault();
      mintRequest.mutate({...formData});
    }}>
      <Stack spacing={2}>
        <TextField id="name" label="Name" variant="outlined"
          autoFocus autoCorrect="off" autoCapitalize="on"
          required disabled={isWorking}
          value={formData.name}
          onChange={(e) => {
            setFormData({...formData, name: e.target.value});
          }}
          error={!isNameValid}
        />
        <DesktopDatePicker label={"Start date"} inputFormat="YYYY-MM-DD"
          disabled={isWorking}
          value={formData.startDate}
          onChange={(newValue: Dayjs | null) => {
            setFormData({...formData, startDate: newValue?.toDate()})
          }}
          renderInput={(params) => <TextField {...params} />}
        />
        <Stack direction={"row"} spacing={2}>
          <SecondaryButton onClick={navBrowserBack}
            disabled={isWorking}>
            Back
          </SecondaryButton>
          <PrimaryActionButton type="submit" context={"minting raid"}
            disabled={!canSubmit}
            isLoading={isWorking}
            error={mintRequest.error}
          >
            Mint RAiD
          </PrimaryActionButton>
        </Stack>
        <CompactErrorPanel error={mintRequest.error} />
      </Stack>
    </form>
  </ContainerCard>
}

function MintRaidHelp(){
  return <HelpPopover content={
    <Stack spacing={1}>
      <ul>
        <li><HelpChip label={"XXX"}/>
          Words.
        </li>
      </ul>
    </Stack>
  }/>;
}