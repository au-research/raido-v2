import { accessGenerateData } from "Forms/RaidForm/components/FormAccessComponent";

import { datesGenerateData } from "Forms/RaidForm/components/FormDatesComponent";
import { descriptionsGenerateData } from "Forms/RaidForm/components/FormDescriptionsComponent";
import { titlesGenerateData } from "Forms/RaidForm/components/FormTitlesComponent";
import { RaidCreateRequest } from "Generated/Raidv2";
import dayjs, { Dayjs } from "dayjs";

export const extractPrefixAndSuffixFromIdentifier = (
  identifier: string
): { prefix: string; suffix: string } => {
  const pattern = /\/([^/]+)\/([^/]+)$/;
  const matches = identifier.match(pattern);

  if (matches && matches.length === 3) {
    return {
      prefix: matches[1],
      suffix: matches[2],
    };
  }

  return {
    prefix: "",
    suffix: "",
  };
};

export const extractKeyFromIdUri = (inputUri: string = ""): string => {
  let result = "";
  const regex = /\/([^/]+)\.json$/;
  const match = inputUri.match(regex);
  if (match && match[1]) {
    result = match[1];
  }
  return result;
};

export const extractLastUrlSegment = (inputUri: string = ""): string => {
  let result = "";
  // Look for a sequence of characters that don't include a slash,
  // located just before a trailing slash (or the end of the string).
  const regex = /\/([^/]+)\/?$/;
  const match = inputUri.match(regex);
  if (match && match[1]) {
    result = match[1];
  }
  return result;
};

// Test
console.log(
  extractKeyFromIdUri(
    "https://credit.niso.org/contributor-roles/formal-analysis/"
  )
);
// Expected output: "formal-analysis"

/**
 * Calculates the date that is three years from the given input date.
 * If no date is provided, it will use the current date.
 *
 * @function
 * @param {string} [inputDate=dayjs().format()] - The starting date in string format.
 * @returns {Dayjs} The date that is three years from the provided or current date.
 * @example
 *   // If today is '2023-09-10'
 *   threeYearsFromDate(); // returns '2026-09-10'
 *   threeYearsFromDate('2020-01-01'); // returns '2023-01-01'
 */
export const threeYearsFromDate = (
  inputDate: string = dayjs().format()
): Dayjs => {
  return dayjs(inputDate).add(3, "year");
};

export const raidColors = new Map([
  ["blue", "#00B0D5"],
  ["pink", "#E51875"],
  ["yellow", "#F8B20E"],
  ["purple", "#8E489B"],
]);

export const newRaid: RaidCreateRequest = {
  title: [titlesGenerateData()],
  description: [descriptionsGenerateData()],
  date: datesGenerateData(),
  access: accessGenerateData(),
  organisation: [
    {
      id: "https://ror.org/038sjwq14",
      schemaUri: "https://ror.org/",
      role: [
        {
          id: "https://github.com/au-research/raid-metadata/blob/main/scheme/organisation/role/v1/contractor.json",
          schemaUri:
            "https://github.com/au-research/raid-metadata/tree/main/scheme/organisation/role/v1/",
          startDate: "2020-01-01",
        },
        {
          id: "https://github.com/au-research/raid-metadata/blob/main/scheme/organisation/role/v1/contractor.json",
          schemaUri:
            "https://github.com/au-research/raid-metadata/tree/main/scheme/organisation/role/v1/",
          startDate: "2022-01-01",
        },
      ],
    },
  ],
  contributor: [
    {
      id: "https://orcid.org/0009-0000-9306-3120",
      schemaUri: "https://orcid.org/",
      position: [
        {
          schemaUri:
            "https://github.com/au-research/raid-metadata/tree/main/scheme/contributor/position/v1/",
          id: "https://github.com/au-research/raid-metadata/blob/main/scheme/contributor/position/v1/leader.json",
          startDate: "2023-08-24",
        },
      ],
      role: [
        {
          schemaUri: "https://credit.niso.org/",
          id: "https://credit.niso.org/contributor-roles/conceptualization/",
        },
      ],
    },
  ],
};
