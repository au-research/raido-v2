package au.org.raid.inttest.endpoint.raidv2.stable;

import au.org.raid.idl.raidv2.model.Organisation;
import au.org.raid.idl.raidv2.model.OrganisationRoleWithSchemeUri;
import au.org.raid.idl.raidv2.model.ValidationFailure;
import au.org.raid.inttest.RaidApiValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static au.org.raid.inttest.endpoint.raidv2.stable.TestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class OrganisationIntegrationTest extends AbstractIntegrationTest {
    @Test
    @DisplayName("Minting a RAiD with no organisations succeeds")
    void noOrganisations() {
        createRequest.setOrganisations(null);

        try {
            raidApi.createRaidV1(createRequest);
        } catch (RaidApiValidationException e) {
            fail("No validation failures expected");
        } catch (Exception e) {
            fail("Expected RaidApiValidationException");
        }
    }

    @Test
    @DisplayName("Minting a RAiD with empty organisations succeeds")
    void emptyOrganisations() {
        createRequest.setOrganisations(Collections.emptyList());

        try {
            raidApi.createRaidV1(createRequest);
        } catch (RaidApiValidationException e) {
            fail("No validation failures expected");
        } catch (Exception e) {
            fail("Expected RaidApiValidationException");
        }
    }

    @Test
    @DisplayName("Minting a RAiD with missing organisation identifierSchemeUri fails")
    void missingIdentifierSchemeUri() {
        createRequest.setOrganisations(List.of(
                new Organisation()
                        .id(VALID_ROR)
                        .roles(List.of(
                                new OrganisationRoleWithSchemeUri()
                                        .schemeUri(ORGANISATION_ROLE_SCHEME_URI)
                                        .id(LEAD_RESEARCH_ORGANISATION_ROLE)
                        ))
        ));

        try {
            raidApi.createRaidV1(createRequest);
            fail("No exception thrown with missing identifierSchemeUri");
        } catch (RaidApiValidationException e) {
            final var failures = e.getFailures();
            assertThat(failures).hasSize(1);
            assertThat(failures).contains(
                    new ValidationFailure()
                            .fieldId("organisations[0].schemeUri")
                            .errorType("notSet")
                            .message("field must be set")
            );
        } catch (Exception e) {
            fail("Expected RaidApiValidationException");
        }
    }

    @Test
    @DisplayName("Minting a RAiD with empty organisation identifierSchemeUri fails")
    void emptyIdentifierSchemeUri() {
        createRequest.setOrganisations(List.of(
                new Organisation()
                        .identifierSchemeUri("")
                        .id(VALID_ROR)
                        .roles(List.of(
                                new OrganisationRoleWithSchemeUri()
                                        .startDate(LocalDate.now())
                                        .schemeUri(ORGANISATION_ROLE_SCHEME_URI)
                                        .id(LEAD_RESEARCH_ORGANISATION)
                        ))
        ));

        try {
            raidApi.createRaidV1(createRequest);
            fail("No exception thrown with empty identifierSchemeUri");
        } catch (RaidApiValidationException e) {
            final var failures = e.getFailures();
            assertThat(failures).hasSize(1);
            assertThat(failures).contains(
                    new ValidationFailure()
                            .fieldId("organisations[0].schemeUri")
                            .errorType("notSet")
                            .message("field must be set")
            );
        } catch (Exception e) {
            fail("Expected RaidApiValidationException");
        }
    }

    @Test
    @DisplayName("Minting a RAiD with missing organisation id fails")
    void missingId() {
        createRequest.setOrganisations(List.of(
                new Organisation()
                        .identifierSchemeUri(ORGANISATION_IDENTIFIER_SCHEME_URI)
                        .roles(List.of(
                                new OrganisationRoleWithSchemeUri()
                                        .schemeUri(ORGANISATION_ROLE_SCHEME_URI)
                                        .id(LEAD_RESEARCH_ORGANISATION_ROLE)
                        ))
        ));

        try {
            raidApi.createRaidV1(createRequest);
            fail("No exception thrown with missing organisation id");
        } catch (RaidApiValidationException e) {
            final var failures = e.getFailures();
            assertThat(failures).hasSize(1);
            assertThat(failures).contains(
                    new ValidationFailure()
                            .fieldId("organisations[0].id")
                            .errorType("notSet")
                            .message("field must be set")
            );
        } catch (Exception e) {
            fail("Expected RaidApiValidationException");
        }
    }

    @Test
    @DisplayName("Minting a RAiD with empty organisation id fails")
    void emptyId() {
        createRequest.setOrganisations(List.of(
                new Organisation()
                        .identifierSchemeUri(ORGANISATION_IDENTIFIER_SCHEME_URI)
                        .id("")
                        .roles(List.of(
                                new OrganisationRoleWithSchemeUri()
                                        .schemeUri(ORGANISATION_ROLE_SCHEME_URI)
                                        .id(LEAD_RESEARCH_ORGANISATION_ROLE)
                        ))
        ));

        try {
            raidApi.createRaidV1(createRequest);
            fail("No exception thrown with empty organisation id");
        } catch (RaidApiValidationException e) {
            final var failures = e.getFailures();
            assertThat(failures).hasSize(1);
            assertThat(failures).contains(
                    new ValidationFailure()
                            .fieldId("organisations[0].id")
                            .errorType("notSet")
                            .message("field must be set")
            );
        } catch (Exception e) {
            fail("Expected RaidApiValidationException");
        }
    }

    @Nested
    @DisplayName("ROR tests...")
    class RorTests {
        @Test
        @DisplayName("Minting a RAiD with invalid ror pattern fails")
        void invalidRorPattern() {
            createRequest.setOrganisations(List.of(
                    new Organisation()
                            .identifierSchemeUri(ORGANISATION_IDENTIFIER_SCHEME_URI)
                            .id("https://ror.org/038sjwqxx")
                            .roles(List.of(
                                    new OrganisationRoleWithSchemeUri()
                                            .schemeUri(ORGANISATION_ROLE_SCHEME_URI)
                                            .id(LEAD_RESEARCH_ORGANISATION_ROLE)
                            ))
            ));

            try {
                raidApi.createRaidV1(createRequest);
                fail("No exception thrown with invalid ror pattern");
            } catch (RaidApiValidationException e) {
                final var failures = e.getFailures();
                assertThat(failures).hasSize(1);
                assertThat(failures).contains(
                        new ValidationFailure()
                                .fieldId("organisations[0].id")
                                .errorType("invalidValue")
                                .message("has invalid/unsupported value")
                );
            } catch (Exception e) {
                fail("Expected RaidApiValidationException");
            }
        }

        @Test
        @DisplayName("Minting a RAiD with non-existent ror fails")
        void nonExistentRor() {
            createRequest.setOrganisations(List.of(
                    new Organisation()
                            .identifierSchemeUri(ORGANISATION_IDENTIFIER_SCHEME_URI)
                            .id("https://ror.org/000000042")
                            .roles(List.of(
                                    new OrganisationRoleWithSchemeUri()
                                            .schemeUri(ORGANISATION_ROLE_SCHEME_URI)
                                            .id(LEAD_RESEARCH_ORGANISATION_ROLE)
                            ))
            ));

            try {
                raidApi.createRaidV1(createRequest);
                fail("No exception thrown with non-existent ror");
            } catch (RaidApiValidationException e) {
                final var failures = e.getFailures();
                assertThat(failures).hasSize(1);
                assertThat(failures).contains(
                        new ValidationFailure()
                                .fieldId("organisations[0].id")
                                .errorType("invalidValue")
                                .message("The organisation ROR does not exist")
                );
            } catch (Exception e) {
                fail("Expected RaidApiValidationException");
            }
        }
    }

    @Nested
    @DisplayName("Role tests...")
    class OrganisationRoleWithSchemeUriTests {
        @Test
        @DisplayName("Minting a RAiD with missing role schemeUri fails")
        void missingRoleSchemeUri() {
            createRequest.setOrganisations(List.of(
                    new Organisation()
                            .identifierSchemeUri(ORGANISATION_IDENTIFIER_SCHEME_URI)
                            .id(VALID_ROR)
                            .roles(List.of(
                                    new OrganisationRoleWithSchemeUri()
                                            .id(LEAD_RESEARCH_ORGANISATION_ROLE)
                            ))
            ));

            try {
                raidApi.createRaidV1(createRequest);
                fail("No exception thrown with missing role schemeUri");
            } catch (RaidApiValidationException e) {
                final var failures = e.getFailures();
                assertThat(failures).hasSize(1);
                assertThat(failures).contains(
                        new ValidationFailure()
                                .fieldId("organisations[0].roles[0].schemeUri")
                                .errorType("notSet")
                                .message("field must be set")
                );
            } catch (Exception e) {
                fail("Expected RaidApiValidationException");
            }
        }

        @Test
        @DisplayName("Minting a RAiD with missing role type fails")
        void missingRoleType() {
            createRequest.setOrganisations(List.of(
                    new Organisation()
                            .identifierSchemeUri(ORGANISATION_IDENTIFIER_SCHEME_URI)
                            .id(VALID_ROR)
                            .roles(List.of(
                                    new OrganisationRoleWithSchemeUri()
                                            .schemeUri(ORGANISATION_ROLE_SCHEME_URI)
                            ))
            ));

            try {
                raidApi.createRaidV1(createRequest);
                fail("No exception thrown with missing role type");
            } catch (RaidApiValidationException e) {
                final var failures = e.getFailures();
                assertThat(failures).hasSize(1);
                assertThat(failures).contains(
                        new ValidationFailure()
                                .fieldId("organisations[0].roles[0].id")
                                .errorType("notSet")
                                .message("field must be set")
                );
            } catch (Exception e) {
                fail("Expected RaidApiValidationException");
            }
        }

        @Test
        @DisplayName("Minting a RAiD with empty role type fails")
        void emptyRoleType() {
            createRequest.setOrganisations(List.of(
                    new Organisation()
                            .identifierSchemeUri(ORGANISATION_IDENTIFIER_SCHEME_URI)
                            .id(VALID_ROR)
                            .roles(List.of(
                                    new OrganisationRoleWithSchemeUri()
                                            .id("")
                                            .schemeUri(ORGANISATION_ROLE_SCHEME_URI)
                            ))
            ));

            try {
                raidApi.createRaidV1(createRequest);
                fail("No exception thrown with empty role type");
            } catch (RaidApiValidationException e) {
                final var failures = e.getFailures();
                assertThat(failures).hasSize(1);
                assertThat(failures).contains(
                        new ValidationFailure()
                                .fieldId("organisations[0].roles[0].id")
                                .errorType("notSet")
                                .message("field must be set")
                );
            } catch (Exception e) {
                fail("Expected RaidApiValidationException");
            }
        }

        @Test
        @DisplayName("Minting a RAiD with invalid role schemeUri fails")
        void invalidRoleSchemeUri() {
            createRequest.setOrganisations(List.of(
                    new Organisation()
                            .identifierSchemeUri(ORGANISATION_IDENTIFIER_SCHEME_URI)
                            .id(VALID_ROR)
                            .roles(List.of(
                                    new OrganisationRoleWithSchemeUri()
                                            .schemeUri("unknown")
                                            .id(LEAD_RESEARCH_ORGANISATION_ROLE)
                            ))
            ));

            try {
                raidApi.createRaidV1(createRequest);
                fail("No exception thrown with invalid role schemeUri");
            } catch (RaidApiValidationException e) {
                final var failures = e.getFailures();
                assertThat(failures).hasSize(1);
                assertThat(failures).contains(
                        new ValidationFailure()
                                .fieldId("organisations[0].roles[0].schemeUri")
                                .errorType("invalidValue")
                                .message("scheme is unknown/unsupported")
                );
            } catch (Exception e) {
                fail("Expected RaidApiValidationException");
            }
        }

        @Test
        @DisplayName("Minting a RAiD with invalid type for role scheme fails")
        void invalidRoleTypeForScheme() {
            createRequest.setOrganisations(List.of(
                    new Organisation()
                            .identifierSchemeUri(ORGANISATION_IDENTIFIER_SCHEME_URI)
                            .id(VALID_ROR)
                            .roles(List.of(
                                    new OrganisationRoleWithSchemeUri()
                                            .schemeUri(ORGANISATION_ROLE_SCHEME_URI)
                                            .id("unknown")
                            ))
            ));

            try {
                raidApi.createRaidV1(createRequest);
                fail("No exception thrown with invalid type for role scheme");
            } catch (RaidApiValidationException e) {
                final var failures = e.getFailures();
                assertThat(failures).hasSize(1);
                assertThat(failures).contains(
                        new ValidationFailure()
                                .fieldId("organisations[0].roles[0].id")
                                .errorType("invalidValue")
                                .message("id does not exist within the given scheme")
                );
            } catch (Exception e) {
                fail("Expected RaidApiValidationException");
            }
        }
    }
}