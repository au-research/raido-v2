import { DisplayItem } from "@/components/DisplayItem";
import { ContributorPositionItem } from "@/entities/contributor-position/display-components/ContributorPositionItem";
import { ContributorRoleItem } from "@/entities/contributor-role/display-components/ContributorRoleItem";
import { Contributor } from "@/generated/raid";
import mapping from "@/mapping.json";
import {
  MappingElement,
  OrcidContributorResponse,
  OrcidLookupResponse,
} from "@/types";
import {
  Box,
  Grid,
  Stack,
  Tooltip,
  Typography,
  darken,
  lighten,
} from "@mui/material";
import {
  NewReleasesOutlined as NewReleasesOutlinedIcon,
  VerifiedOutlined as VerifiedOutlinedIcon,
} from "@mui/icons-material";
import { Link } from "react-router-dom";

export default function ContributorItem({
  contributor,
  orcidContributors,
  orcidLookup,
}: {
  contributor: Contributor;
  orcidContributors: OrcidContributorResponse[];
  orcidLookup: OrcidLookupResponse[];
}) {
  const contributorType = mapping.find(
    (el: MappingElement) => el.id === contributor.position[0].id
  )?.value;

  const orcidContributor = orcidContributors.find(
    (el) => el.contributorUuid === contributor.uuid
  );

  const orcidlookupEntry = orcidLookup.find(
    (el) => el.contributorUuid === contributor.uuid
  );

  return (
    <Stack gap={2}>
      <Grid container spacing={2}>
        {orcidlookupEntry && (
          <>
            <Grid item xs={12} sm={12}>
              <Box
                sx={{
                  backgroundColor: (theme) => {
                    return theme.palette.mode === "dark"
                      ? darken(theme.palette.action.selected, 0.4)
                      : lighten(theme.palette.action.selected, 0.25);
                  },
                  borderRadius: "4px",
                  padding: "10px 12px",
                  color: "text.primary",
                  display: "flex",
                  flexDirection: "column",
                }}
              >
                <Typography variant="body2">Email</Typography>
                <Box
                  sx={{
                    display: "inline-flex",
                    justifyContent: "space-between",
                  }}
                >
                  <Typography color="text.secondary" variant="body1" noWrap>
                    {orcidlookupEntry.email}
                  </Typography>
                  <Tooltip
                    title={`Contributor added: ${new Date(
                      orcidlookupEntry.createdOn
                    ).toLocaleString()}`}
                  >
                    <Typography
                      color="text.error"
                      variant="body1"
                      noWrap
                      sx={{ display: "inline-flex" }}
                    >
                      <NewReleasesOutlinedIcon color="warning" sx={{ mr: 1 }} />
                      <small>pending verification</small>
                    </Typography>
                  </Tooltip>
                </Box>
              </Box>
            </Grid>
          </>
        )}

        {orcidContributor && (
          <>
            <Grid item xs={12} sm={6}>
              <Box
                sx={{
                  backgroundColor: (theme) => {
                    return theme.palette.mode === "dark"
                      ? darken(theme.palette.action.selected, 0.4)
                      : lighten(theme.palette.action.selected, 0.25);
                  },
                  borderRadius: "4px",
                  padding: "10px 12px",
                  color: "text.primary",
                  display: "flex",
                  flexDirection: "column",
                }}
              >
                <Typography variant="body2">Name</Typography>
                <Typography color="text.secondary" variant="body1" noWrap>
                  {orcidContributor.name}
                </Typography>
              </Box>
            </Grid>
            <Grid item xs={12} sm={6}>
              <Box
                sx={{
                  backgroundColor: (theme) => {
                    return theme.palette.mode === "dark"
                      ? darken(theme.palette.action.selected, 0.4)
                      : lighten(theme.palette.action.selected, 0.25);
                  },
                  borderRadius: "4px",
                  padding: "10px 12px",
                  color: "text.primary",
                  display: "flex",
                  flexDirection: "column",
                }}
              >
                <Typography variant="body2">ORCID</Typography>
                <Box
                  sx={{
                    display: "inline-flex",
                    justifyContent: "space-between",
                  }}
                >
                  <Typography
                    component={Link}
                    color="text.secondary"
                    variant="body1"
                    noWrap
                    target="__blank"
                    to={`https://sandbox.orcid.org/${orcidContributor.orcid}`}
                  >
                    {orcidContributor.orcid}
                  </Typography>
                  <Tooltip
                    title={`Verified: ${new Date(
                      orcidContributor.createdOn
                    ).toLocaleString()}`}
                  >
                    <Typography
                      color="success"
                      variant="body1"
                      noWrap
                      sx={{ display: "inline-flex" }}
                    >
                      <VerifiedOutlinedIcon color="success" sx={{ mr: 1 }} />
                      <small>verified</small>
                    </Typography>
                  </Tooltip>
                </Box>
              </Box>
            </Grid>
          </>
        )}

        {!orcidContributor && !orcidlookupEntry && (
          <DisplayItem label="ID" value={contributor.uuid} width={12} />
        )}

        <DisplayItem label="Type" value={contributorType} width={3} />
        <DisplayItem
          label="Leader"
          value={contributor.leader ? "Yes" : "No"}
          width={2}
        />
        <DisplayItem
          label="Contact"
          value={contributor.contact ? "Yes" : "No"}
          width={2}
        />
      </Grid>

      <Stack sx={{ paddingLeft: 3 }} gap={1}>
        <Typography variant="body1">Position</Typography>
        {contributor.position.map((position, i) => (
          <ContributorPositionItem key={i} contributorPosition={position} />
        ))}

        <Typography variant="body1">Roles</Typography>
        <Grid container gap={1}>
          {contributor.role
            .sort((a, b) => a.id.localeCompare(b.id))
            .map((role, i) => (
              <ContributorRoleItem key={i} contributorRole={role} />
            ))}
        </Grid>
      </Stack>
    </Stack>
  );
}