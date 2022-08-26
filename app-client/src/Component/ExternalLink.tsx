import * as React from "react";
import {OpenInNew} from "@mui/icons-material";

export const supportEmail = "web.services@ardc.edu.au";

export const muiUrl = "https://mui.com";

export function NewWindowLink(props: {
  href: string,
  children: React.ReactNode,
}){
  return <a href={props.href}
    target="_blank" rel="noopener noreferrer"
    style={{whiteSpace: "nowrap"}}
  >
    {props.children}{" "}
    <OpenInNew style={{
      // align with text better
      verticalAlign: "bottom"
    }}/>
  </a>
}