import {
  isPagePath,
  NavTransition,
  useNavigation
} from "Design/NavigationProvider";
import { raidoTitle } from "Component/Util";
import { LargeContentMain } from "Design/LayoutMain";
import { ContainerCard } from "Design/ContainerCard";
import { TextSpan } from "Component/TextSpan";
import React, { useState } from "react";
import { normalisePath } from "Util/Location";
import { RqQuery } from "Util/ReactQueryUtil";
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { ServicePoint, UpdateServicePointRequest } from "Generated/Raidv2";
import { useAuthApi } from "Api/AuthApi";
import { CompactErrorPanel } from "Error/CompactErrorPanel";
import {
  Checkbox,
  FormControl,
  FormControlLabel,
  Stack,
  TextField
} from "@mui/material";
import { PrimaryActionButton, SecondaryButton } from "Component/AppButton";
import { navBrowserBack } from "Util/WindowUtil";

const log = console;

const pageUrl = "/service-point";

const idParamName = "servicePointId";

export function getServicePointPageLink(servicePointId: number|undefined): string{
  return `${pageUrl}?${idParamName}=${servicePointId}`;
}

export function getServicePointIdFromLocation(): number | undefined{
  // TODO:STO change to using path param instead of search param
  const urlParams = new URLSearchParams(window.location.search);
  let value = urlParams.get(idParamName);
  if( !value ){
    return undefined;
  }
  return Number(value);
}

export function isServicePointPagePath(path: string): boolean{
  return normalisePath(path).startsWith(pageUrl);
}

export function ServicePointPage(){
  return <NavTransition isPagePath={(pathname)=>isPagePath(pathname, pageUrl)}
    title={raidoTitle("Service point")}
  >
    <Content/>
  </NavTransition>
}


function Content(){
  const [servicePointId, setServicePointId] = 
    useState(getServicePointIdFromLocation());
  const nav = useNavigation()

  return <LargeContentMain>
    <ServicePointContainer servicePointId={servicePointId}
      onCreate={(createdId)=>{
        nav.replace(getServicePointPageLink(createdId));
        setServicePointId(createdId);
      }}
    />
  </LargeContentMain>
}

function ServicePointContainer({servicePointId, onCreate}: {
  servicePointId: number|undefined, 
  onCreate: (servicePointId: number)=>void,
}){
  const api = useAuthApi();
  const queryClient = useQueryClient();
  const queryName = 'readServicePoint';
  const [formData, setFormData] = useState({
    techEmail: "", adminEmail: "", enabled: true
  } as ServicePoint );
  const query: RqQuery<ServicePoint> = useQuery(
    [queryName, servicePointId],
    async () => {
      if( servicePointId ){
        let servicePoint = await api.admin.readServicePoint({
          servicePointId: servicePointId
        });
        setFormData(servicePoint);
        return servicePoint;
      }
      else {
        return formData;
      }
    }
  );
  const updateRequest = useMutation(
    async (data: UpdateServicePointRequest) => {
      const result = await api.admin.updateServicePoint(data);
      if( !servicePointId ){
        onCreate(result.id);
      }
    },
    {
      onSuccess: async () => {
        await queryClient.invalidateQueries([queryName]);
      },
    }
  );

  if( query.error ){
    return <CompactErrorPanel error={query.error}/>
  }

  if( !query.data ){
    if( query.isLoading ){
      return <TextSpan>loading...</TextSpan>
    }
    else {
      console.log("unexpected state", query);
      return <TextSpan>unexpected state</TextSpan>
    }
  }

  const isWorking = query.isLoading || updateRequest.isLoading;
  
  return <ContainerCard title={"Authorisation request"}>
    <form autoComplete="off" onSubmit={(e) => {
      e.preventDefault();
      updateRequest.mutate({servicePoint: formData});
    }}>
    <Stack spacing={2}>
      <FormControl focused autoCorrect="off" autoCapitalize="on">
        <TextField id="name" label="Name" variant="outlined"
          disabled={isWorking}
          value={formData.name || ''}
          onChange={(e) => {
            setFormData({...formData, name: e.target.value});
          }}
        />
      </FormControl>
      <FormControl>
        <TextField id="adminEmail" label="Admin email" variant="outlined"
          disabled={isWorking}
          value={formData.adminEmail || ''}
          onChange={(e) => {
            setFormData({...formData, adminEmail: e.target.value});
          }}
        />
      </FormControl>
      <TextField id="techEmail" label="Tech email" variant="outlined"
        disabled={isWorking}
        value={formData.techEmail || ''}
        onChange={(e) => {
          setFormData({...formData, techEmail: e.target.value});
        }}
      />
      <FormControl>
        <FormControlLabel
          disabled={isWorking}
          label="Enabled"
          labelPlacement="start"
          style={{
            /* by default, MUI lays this out as <checkbox><label>.
             Doing `labelPlacement=start`, flips that around, but ends up 
             right-justigying the content, so `marginRight=auto` pushes it back 
             across to the left and `marginLeft=0` aligns nicely. */
            marginLeft: 0,
            marginRight: "auto", 
        }}
          control={
            <Checkbox
              checked={formData.enabled ?? true}
              onChange={()=>{
                setFormData({...formData, enabled: !formData.enabled})
              }}
            />          
          }
        />        
      </FormControl>
      <Stack direction={"row"} spacing={2}>
        <SecondaryButton onClick={navBrowserBack}
          disabled={updateRequest.isLoading}>
          Back
        </SecondaryButton>
        <PrimaryActionButton type="submit" context={"update service point"}
          disabled={isWorking || !formData.name}
          isLoading={updateRequest.isLoading}
          error={updateRequest.error}
        >
          { servicePointId ? "Update" : "Create" }
        </PrimaryActionButton>
      </Stack>
    </Stack>
  </form>
</ContainerCard>
}