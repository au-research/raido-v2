import { faker } from "@faker-js/faker";
import { Container } from "@mui/material";
import { useMutation, useQuery } from "@tanstack/react-query";
import { useAuthApi } from "Api/AuthApi";
import { raidoTitle } from "Component/Util";
import {
  NavPathResult,
  NavTransition,
  NavigationState,
  isPagePath,
  parsePageSuffixParams,
  useNavigation,
} from "Design/NavigationProvider";
import RaidForm from "Forms/RaidForm";
import {
  Access,
  Contributor,
  CreateRaidV1Request,
  Dates,
  RaidDto,
  Title,
} from "Generated/Raidv2";

import { useState } from "react";
import { newRaid } from "utils";

const pageUrl = "/mint-raid-new";

export function isMintRaidPagePath(pathname: string): NavPathResult {
  return isPagePath(pathname, pageUrl);
}

function getRaidHandleFromPathname(nav: NavigationState): string {
  return parsePageSuffixParams<string>(nav, isMintRaidPagePath, String);
}

function Content() {
  const nav = useNavigation();

  const handleRaidCreate = async (data: RaidDto): Promise<RaidDto> => {
    return await api.raid.createRaidV1({
      createRaidV1Request: {
        titles: data?.titles || ([] as Title[]),
        access: data?.access || ({} as Access),
        dates: data?.dates || ({} as Dates),
        contributors: data?.contributors || ([] as Contributor[]),
      },
    });
  };

  const api = useAuthApi();
  const mintRequest = useMutation(handleRaidCreate, {
    onSuccess: (mintResult) => {
      console.log("mintResult", mintResult);
    },
    onError: (error) => {
      console.log("error", error);
    },
  });

  const defaultValues = newRaid;

  return (
    <Container maxWidth="lg">
      <RaidForm
        defaultValues={defaultValues}
        onSubmit={async (data) => {
          console.log(JSON.stringify(data, null, 2));
          // mintRequest.mutate(data);
        }}
        isSubmitting={mintRequest.isLoading}
        formTitle="Mint new RAiD"
      />
    </Container>
  );
}

export default function MintRaidPage() {
  return (
    <NavTransition
      isPagePath={isMintRaidPagePath}
      title={raidoTitle("Mint RAiD")}
    >
      <Content />
    </NavTransition>
  );
}
