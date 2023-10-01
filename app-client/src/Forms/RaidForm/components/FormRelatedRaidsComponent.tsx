import { faker } from "@faker-js/faker";
import {
  AddCircleOutline as AddCircleOutlineIcon,
  RemoveCircleOutline as RemoveCircleOutlineIcon,
} from "@mui/icons-material";
import {
  Autocomplete,
  Box,
  Card,
  CardContent,
  CardHeader,
  Divider,
  Grid,
  IconButton,
  MenuItem,
  Paper,
  Stack,
  TextField,
  Tooltip,
  Typography,
} from "@mui/material";
import { useQuery } from "@tanstack/react-query";
import { useAuthApi } from "Api/AuthApi";
import { useAuth } from "Auth/AuthProvider";
import { ListRaidsV1Request, RaidDto } from "Generated/Raidv2";
import { RqQuery } from "Util/ReactQueryUtil";
import {
  Control,
  Controller,
  FieldErrors,
  UseFormTrigger,
  useFieldArray,
} from "react-hook-form";
import { relatedRaidTypes } from "../../../references";

export default function FormRelatedRaidsComponent({
  control,
  errors,
  color,
  trigger,
}: {
  control: Control<RaidDto, any>;
  errors: FieldErrors<RaidDto>;
  color: string;
  trigger: UseFormTrigger<RaidDto>;
}) {
  const api = useAuthApi();
  const {
    session: { payload: user },
  } = useAuth();

  const servicePointId = user?.servicePointId || 20000000;
  const listRaids = async ({ servicePointId }: ListRaidsV1Request) => {
    return await api.raid.listRaidsV1({
      servicePointId,
    });
  };

  const relatedRaidsFieldArray = useFieldArray({
    control,
    name: "relatedRaid",
  });

  const raidQuery: RqQuery<RaidDto[]> = useQuery(
    ["listRaids", servicePointId],
    () => listRaids({ servicePointId })
  );

  if (raidQuery.error) {
    return <div>Error...</div>;
  }

  if (raidQuery.isLoading) {
    return <div>Loading...</div>;
  }

  const handleAddRelatedRaids = () => {
    relatedRaidsFieldArray.append({
      // id: faker.string.uuid(),
      type: {
        id: "isPartOf",
        schemaUri: "https://linked.data.gov.au/def/anzsrc-for/2020/",
      },
    });
  };

  return (
    <Card
      variant="outlined"
      sx={{
        borderLeft: "solid",
        borderLeftColor: color,
        borderLeftWidth: 3,
      }}
    >
      <CardHeader
        title={
          <Typography variant="h6" component="div">
            Related RAiDs
          </Typography>
        }
        action={
          <Tooltip title="Related Raid" placement="right">
            <IconButton
              aria-label="Related Raid"
              onClick={handleAddRelatedRaids}
            >
              <AddCircleOutlineIcon />
            </IconButton>
          </Tooltip>
        }
      />
      <CardContent>
        <Stack gap={2}>
          {relatedRaidsFieldArray.fields.length === 0 && (
            <Typography
              variant="body2"
              color={"text.secondary"}
              textAlign={"center"}
            >
              No relates RAiDs defined
            </Typography>
          )}
          {relatedRaidsFieldArray.fields.map((field, index) => {
            return (
              <Box
                sx={{
                  bgcolor: "rgba(0, 0, 0, 0.02)",
                  p: 2,
                  borderRadius: 2,
                }}
                key={field.id}
              >
                <Controller
                  control={control}
                  name={`relatedRaid.${index}`}
                  render={({ field: { onChange, ...controllerField } }) => {
                    return (
                      <>
                        <Stack direction="row" alignItems="flex-start" gap={1}>
                          <Grid container spacing={2}>
                            <Grid item xs={12} sm={6} md={4}>
                              <TextField
                                select
                                {...controllerField}
                                value={controllerField?.value?.type?.id}
                                size="small"
                                fullWidth
                                label="Relation Type"
                                onChange={(event) => {
                                  onChange({
                                    ...controllerField.value,
                                    type: {
                                      ...controllerField?.value?.type,
                                      id: event.target.value,
                                    },
                                  });
                                }}
                              >
                                {relatedRaidTypes.map((relatedRaidType) => (
                                  <MenuItem
                                    key={relatedRaidType.uri}
                                    value={relatedRaidType.name}
                                  >
                                    {relatedRaidType.name}
                                  </MenuItem>
                                ))}
                              </TextField>
                            </Grid>
                            <Grid item xs={12} sm={12} md={8}>
                              <Controller
                                name={`relatedRaid.${index}.id`}
                                control={control}
                                defaultValue=""
                                rules={{ required: true }}
                                render={({
                                  field: { onChange, value },
                                  fieldState: { error },
                                }) => (
                                  <Autocomplete
                                    options={raidQuery.data || []}
                                    getOptionLabel={(option) => {
                                      // const identifierParts =
                                      //   option?.id?.identifier.split("/");
                                      const identifierParts =
                                        option.identifier.id.split("/");

                                      const identifierPartsLength =
                                        identifierParts?.length;

                                      const prefix = identifierPartsLength
                                        ? identifierParts?.[
                                            identifierPartsLength - 2
                                          ]
                                        : "";
                                      const suffix = identifierPartsLength
                                        ? identifierParts?.[
                                            identifierPartsLength - 1
                                          ]
                                        : "";
                                      return `${prefix}/${suffix}: ${
                                        option?.title?.map((el) => el.text) ||
                                        ""
                                      }`;
                                    }}
                                    onChange={(_, newValue) => {
                                      onChange(
                                        newValue ? newValue.identifier.id : ""
                                      );
                                    }}
                                    isOptionEqualToValue={(option, value) => {
                                      return (
                                        option.identifier.id ===
                                        value.identifier.id
                                      );
                                    }}
                                    renderInput={(params) => (
                                      <TextField
                                        {...params}
                                        size="small"
                                        label="Related RAiD"
                                        error={!!error}
                                        helperText={
                                          error
                                            ? "This field is required"
                                            : null
                                        }
                                      />
                                    )}
                                  />
                                )}
                              />
                            </Grid>
                          </Grid>
                          <Tooltip
                            title="Remove Related Raid"
                            placement="right"
                          >
                            <IconButton
                              aria-label="Remove Related Raid"
                              onClick={() =>
                                relatedRaidsFieldArray.remove(index)
                              }
                            >
                              <RemoveCircleOutlineIcon />
                            </IconButton>
                          </Tooltip>
                        </Stack>
                      </>
                    );
                  }}
                />
              </Box>
            );
          })}
        </Stack>
      </CardContent>
    </Card>
  );
}