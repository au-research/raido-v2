package au.org.raid.api.factory;

import au.org.raid.idl.raidv2.model.ContributorRole;
import au.org.raid.idl.raidv2.model.ContributorRoleCreditNisoOrgType;
import au.org.raid.idl.raidv2.model.ContributorRoleSchemeType;
import au.org.raid.idl.raidv2.model.ContributorRoleWithSchemeUri;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

class ContributorRoleFactoryTest {
    private static final String SCHEME_URI =
            "https://credit.niso.org/";

    private final ContributorRoleFactory roleFactory = new ContributorRoleFactory();

    @Test
    @DisplayName("Conceptualization is set")
    void leaderSet() {
        final var role = new ContributorRole()
                .role(ContributorRoleCreditNisoOrgType.CONCEPTUALIZATION)
                .roleSchemeUri(ContributorRoleSchemeType.HTTPS_CREDIT_NISO_ORG_);

        final var expected = new ContributorRoleWithSchemeUri()
                .id("https://credit.niso.org/contributor-roles/conceptualization/")
                .schemeUri(SCHEME_URI);

        final var result = roleFactory.create(role);

        assertThat(result, is(expected));
    }

    @Test
    @DisplayName("Data curation is set")
    void dataCurationSet() {
        final var role = new ContributorRole()
                .role(ContributorRoleCreditNisoOrgType.DATA_CURATION)
                .roleSchemeUri(ContributorRoleSchemeType.HTTPS_CREDIT_NISO_ORG_);

        final var expected = new ContributorRoleWithSchemeUri()
                .id("https://credit.niso.org/contributor-roles/data-curation/")
                .schemeUri(SCHEME_URI);

        final var result = roleFactory.create(role);

        assertThat(result, is(expected));
    }

    @Test
    @DisplayName("Formal analysis is set")
    void formalAnalysisSet() {
        final var role = new ContributorRole()
                .role(ContributorRoleCreditNisoOrgType.FORMAL_ANALYSIS)
                .roleSchemeUri(ContributorRoleSchemeType.HTTPS_CREDIT_NISO_ORG_);

        final var expected = new ContributorRoleWithSchemeUri()
                .id("https://credit.niso.org/contributor-roles/formal-analysis/")
                .schemeUri(SCHEME_URI);

        final var result = roleFactory.create(role);

        assertThat(result, is(expected));
    }

    @Test
    @DisplayName("Funding acquisition is set")
    void fundingAcquisitionSet() {
        final var role = new ContributorRole()
                .role(ContributorRoleCreditNisoOrgType.FUNDING_ACQUISITION)
                .roleSchemeUri(ContributorRoleSchemeType.HTTPS_CREDIT_NISO_ORG_);

        final var expected = new ContributorRoleWithSchemeUri()
                .id("https://credit.niso.org/contributor-roles/funding-acquisition/")
                .schemeUri(SCHEME_URI);

        final var result = roleFactory.create(role);

        assertThat(result, is(expected));
    }

    @Test
    @DisplayName("Investigation is set")
    void investigationSet() {
        final var role = new ContributorRole()
                .role(ContributorRoleCreditNisoOrgType.INVESTIGATION)
                .roleSchemeUri(ContributorRoleSchemeType.HTTPS_CREDIT_NISO_ORG_);

        final var expected = new ContributorRoleWithSchemeUri()
                .id("https://credit.niso.org/contributor-roles/investigation/")
                .schemeUri(SCHEME_URI);

        final var result = roleFactory.create(role);

        assertThat(result, is(expected));
    }

    @Test
    @DisplayName("Methodology is set")
    void methodologySet() {
        final var role = new ContributorRole()
                .role(ContributorRoleCreditNisoOrgType.METHODOLOGY)
                .roleSchemeUri(ContributorRoleSchemeType.HTTPS_CREDIT_NISO_ORG_);

        final var expected = new ContributorRoleWithSchemeUri()
                .id("https://credit.niso.org/contributor-roles/methodology/")
                .schemeUri(SCHEME_URI);

        final var result = roleFactory.create(role);

        assertThat(result, is(expected));
    }

    @Test
    @DisplayName("Project administration is set")
    void projectAdministrationSet() {
        final var role = new ContributorRole()
                .role(ContributorRoleCreditNisoOrgType.PROJECT_ADMINISTRATION)
                .roleSchemeUri(ContributorRoleSchemeType.HTTPS_CREDIT_NISO_ORG_);

        final var expected = new ContributorRoleWithSchemeUri()
                .id("https://credit.niso.org/contributor-roles/project-administration/")
                .schemeUri(SCHEME_URI);

        final var result = roleFactory.create(role);

        assertThat(result, is(expected));
    }

    @Test
    @DisplayName("Resources is set")
    void resourcesSet() {
        final var role = new ContributorRole()
                .role(ContributorRoleCreditNisoOrgType.RESOURCES)
                .roleSchemeUri(ContributorRoleSchemeType.HTTPS_CREDIT_NISO_ORG_);

        final var expected = new ContributorRoleWithSchemeUri()
                .id("https://credit.niso.org/contributor-roles/resources/")
                .schemeUri(SCHEME_URI);

        final var result = roleFactory.create(role);

        assertThat(result, is(expected));
    }

    @Test
    @DisplayName("Software is set")
    void softwareSet() {
        final var role = new ContributorRole()
                .role(ContributorRoleCreditNisoOrgType.SOFTWARE)
                .roleSchemeUri(ContributorRoleSchemeType.HTTPS_CREDIT_NISO_ORG_);

        final var expected = new ContributorRoleWithSchemeUri()
                .id("https://credit.niso.org/contributor-roles/software/")
                .schemeUri(SCHEME_URI);

        final var result = roleFactory.create(role);

        assertThat(result, is(expected));
    }

    @Test
    @DisplayName("Supervision is set")
    void supervisionSet() {
        final var role = new ContributorRole()
                .role(ContributorRoleCreditNisoOrgType.SUPERVISION)
                .roleSchemeUri(ContributorRoleSchemeType.HTTPS_CREDIT_NISO_ORG_);

        final var expected = new ContributorRoleWithSchemeUri()
                .id("https://credit.niso.org/contributor-roles/supervision/")
                .schemeUri(SCHEME_URI);

        final var result = roleFactory.create(role);

        assertThat(result, is(expected));
    }

    @Test
    @DisplayName("Validation is set")
    void validationSet() {
        final var role = new ContributorRole()
                .role(ContributorRoleCreditNisoOrgType.VALIDATION)
                .roleSchemeUri(ContributorRoleSchemeType.HTTPS_CREDIT_NISO_ORG_);

        final var expected = new ContributorRoleWithSchemeUri()
                .id("https://credit.niso.org/contributor-roles/validation/")
                .schemeUri(SCHEME_URI);

        final var result = roleFactory.create(role);

        assertThat(result, is(expected));
    }

    @Test
    @DisplayName("Visualization is set")
    void visualizationSet() {
        final var role = new ContributorRole()
                .role(ContributorRoleCreditNisoOrgType.VISUALIZATION)
                .roleSchemeUri(ContributorRoleSchemeType.HTTPS_CREDIT_NISO_ORG_);

        final var expected = new ContributorRoleWithSchemeUri()
                .id("https://credit.niso.org/contributor-roles/visualization/")
                .schemeUri(SCHEME_URI);

        final var result = roleFactory.create(role);

        assertThat(result, is(expected));
    }

    @Test
    @DisplayName("Writing original draft is set")
    void WritingOriginalDraftSet() {
        final var role = new ContributorRole()
                .role(ContributorRoleCreditNisoOrgType.WRITING_ORIGINAL_DRAFT)
                .roleSchemeUri(ContributorRoleSchemeType.HTTPS_CREDIT_NISO_ORG_);

        final var expected = new ContributorRoleWithSchemeUri()
                .id("https://credit.niso.org/contributor-roles/writing-original-draft/")
                .schemeUri(SCHEME_URI);

        final var result = roleFactory.create(role);

        assertThat(result, is(expected));
    }

    @Test
    @DisplayName("Writing review and editing is set")
    void WritingReviewAndEditingSet() {
        final var role = new ContributorRole()
                .role(ContributorRoleCreditNisoOrgType.WRITING_REVIEW_EDITING)
                .roleSchemeUri(ContributorRoleSchemeType.HTTPS_CREDIT_NISO_ORG_);

        final var expected = new ContributorRoleWithSchemeUri()
                .id("https://credit.niso.org/contributor-roles/writing-review-editing/")
                .schemeUri(SCHEME_URI);

        final var result = roleFactory.create(role);

        assertThat(result, is(expected));
    }

    @Test
    @DisplayName("Null ContributorRole returns null")
    void returnsNull() {
        assertThat(roleFactory.create(null), nullValue());
    }

    @Test
    @DisplayName("ContributorRole with empty fields does not throw NullPointerException")
    void emptyFields() {
        assertThat(roleFactory.create(new ContributorRole()), is(new ContributorRoleWithSchemeUri()
                .id(null)
                .schemeUri(SCHEME_URI)));
    }
}