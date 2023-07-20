package raido.inttest.endpoint.raidv2.stable;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import raido.idl.raidv2.model.ContribPosition;
import raido.idl.raidv2.model.ContribRole;
import raido.idl.raidv2.model.Contributor;
import raido.idl.raidv2.model.ValidationFailure;
import raido.inttest.RaidApiValidationException;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static raido.inttest.endpoint.raidv2.stable.TestConstants.*;

public class ContributorsIntegrationTest extends AbstractStableIntegrationTest {
  @Test
  @DisplayName("Minting a RAiD with no contributors fails")
  void noContributors() {
    createRequest.setContributors(null);

    try {
      raidApi.createRaidV1(createRequest);
      fail("No exception thrown with no contrbutors");
    } catch (RaidApiValidationException e) {
      final var failures = e.getFailures();
      assertThat(failures).hasSize(1);
      assertThat(failures).contains(
        new ValidationFailure()
          .fieldId("contributors")
          .errorType("notSet")
          .message("field must be set")
      );
    } catch (Exception e) {
      fail("Expected RaidApiValidationException");
    }
  }

  @Test
  @DisplayName("Minting a RAiD with empty contributors fails")
  void emptyContributors() {
    createRequest.setContributors(Collections.emptyList());

    try {
      raidApi.createRaidV1(createRequest);
      fail("No exception thrown with empty contributors");
    } catch (RaidApiValidationException e) {
      final var failures = e.getFailures();
      assertThat(failures).hasSize(1);
      assertThat(failures).contains(
        new ValidationFailure()
          .fieldId("contributors")
          .errorType("notSet")
          .message("field must be set")
      );
    } catch (Exception e) {
      fail("Expected RaidApiValidationException");
    }
  }

  @Test
  @DisplayName("Minting a RAiD with missing identifierSchemeUri fails")
  void missingIdentifierSchemeUri() {
    createRequest.setContributors(List.of(
      new Contributor()
        .id("https://orcid.org/0000-0000-0000-0001")
        .positions(List.of(
          new ContribPosition()
            .startDate(LocalDate.now())
            .schemeUri(CONTRIBUTOR_POSITION_SCHEME_URI)
            .type(LEADER_POSITION)
        ))
        .roles(List.of(
          new ContribRole()
            .schemeUri(CONTRIBUTOR_ROLE_SCHEME_URI)
            .type(SOFTWARE_CONTRIBUTOR_ROLE)
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
          .fieldId("contributors[0].schemeUri")
          .errorType("notSet")
          .message("field must be set")
      );
    } catch (Exception e) {
      fail("Expected RaidApiValidationException");
    }
  }

  @Test
  @DisplayName("Minting a RAiD with empty identifierSchemeUri fails")
  void emptyIdentifierSchemeUri() {
    createRequest.setContributors(List.of(
      new Contributor()
        .identifierSchemeUri("")
        .id("https://orcid.org/0000-0000-0000-0001")
        .positions(List.of(
          new ContribPosition()
            .startDate(LocalDate.now())
            .schemeUri(CONTRIBUTOR_POSITION_SCHEME_URI)
            .type(LEADER_POSITION)
        ))
        .roles(List.of(
          new ContribRole()
            .schemeUri(CONTRIBUTOR_ROLE_SCHEME_URI)
            .type(SOFTWARE_CONTRIBUTOR_ROLE)
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
          .fieldId("contributors[0].schemeUri")
          .errorType("notSet")
          .message("field must be set")
      );
    } catch (Exception e) {
      fail("Expected RaidApiValidationException");
    }
  }

  @Test
  @DisplayName("Minting a RAiD with missing contributor id fails")
  void missingId() {
    createRequest.setContributors(List.of(
      new Contributor()
        .identifierSchemeUri(CONTRIBUTOR_SCHEME_URI)
        .positions(List.of(
          new ContribPosition()
            .startDate(LocalDate.now())
            .schemeUri(CONTRIBUTOR_POSITION_SCHEME_URI)
            .type(LEADER_POSITION)
        ))
        .roles(List.of(
          new ContribRole()
            .schemeUri(CONTRIBUTOR_ROLE_SCHEME_URI)
            .type(SOFTWARE_CONTRIBUTOR_ROLE)
        ))
    ));

    try {
      raidApi.createRaidV1(createRequest);
      fail("No exception thrown with missing contributor id");
    } catch (RaidApiValidationException e) {
      final var failures = e.getFailures();
      assertThat(failures).hasSize(1);
      assertThat(failures).contains(
        new ValidationFailure()
          .fieldId("contributors[0].id")
          .errorType("notSet")
          .message("field must be set")
      );
    } catch (Exception e) {
      fail("Expected RaidApiValidationException");
    }
  }

  @Test
  @DisplayName("Minting a RAiD with empty contributor id fails")
  void emptyId() {
    createRequest.setContributors(List.of(
      new Contributor()
        .identifierSchemeUri(CONTRIBUTOR_SCHEME_URI)
        .id("")
        .positions(List.of(
          new ContribPosition()
            .startDate(LocalDate.now())
            .schemeUri(CONTRIBUTOR_POSITION_SCHEME_URI)
            .type(LEADER_POSITION)
        ))
        .roles(List.of(
          new ContribRole()
            .schemeUri(CONTRIBUTOR_ROLE_SCHEME_URI)
            .type(SOFTWARE_CONTRIBUTOR_ROLE)
        ))
    ));

    try {
      raidApi.createRaidV1(createRequest);
      fail("No exception thrown with empty contributor id");
    } catch (RaidApiValidationException e) {
      final var failures = e.getFailures();
      assertThat(failures).hasSize(1);
      assertThat(failures).contains(
        new ValidationFailure()
          .fieldId("contributors[0].id")
          .errorType("notSet")
          .message("field must be set")
      );
    } catch (Exception e) {
      fail("Expected RaidApiValidationException");
    }
  }

  @Nested
  @DisplayName("Orcid tests...")
  class OrcidTests {
    @Test
    @DisplayName("Minting a RAiD with invalid orcid pattern fails")
    void invalidOrcidPattern() {
      createRequest.setContributors(List.of(
        new Contributor()
          .identifierSchemeUri(CONTRIBUTOR_SCHEME_URI)
          .id("https://orcid.org/0000-0c00-0000-0000")
          .positions(List.of(
            new ContribPosition()
              .startDate(LocalDate.now())
              .schemeUri(CONTRIBUTOR_POSITION_SCHEME_URI)
              .type(LEADER_POSITION)
          ))
          .roles(List.of(
            new ContribRole()
              .schemeUri(CONTRIBUTOR_ROLE_SCHEME_URI)
              .type(SOFTWARE_CONTRIBUTOR_ROLE)
          ))
      ));

      try {
        raidApi.createRaidV1(createRequest);
        fail("No exception thrown with invalid orcid pattern");
      } catch (RaidApiValidationException e) {
        final var failures = e.getFailures();
        assertThat(failures).hasSize(1);
        assertThat(failures).contains(
          new ValidationFailure()
            .fieldId("contributors[0].id")
            .errorType("invalidValue")
            .message("Contributor ORCID should have the format https://orcid.org/0000-0000-0000-0000")
        );
      } catch (Exception e) {
        fail("Expected RaidApiValidationException");
      }
    }

    @Test
    @DisplayName("Minting a RAiD with invalid orcid checksum fails")
    void invalidOrcidChecksum() {
      createRequest.setContributors(List.of(
        new Contributor()
          .identifierSchemeUri(CONTRIBUTOR_SCHEME_URI)
          .id("https://orcid.org/0000-0000-0000-0000")
          .positions(List.of(
            new ContribPosition()
              .startDate(LocalDate.now())
              .schemeUri(CONTRIBUTOR_POSITION_SCHEME_URI)
              .type(LEADER_POSITION)
          ))
          .roles(List.of(
            new ContribRole()
              .schemeUri(CONTRIBUTOR_ROLE_SCHEME_URI)
              .type(SOFTWARE_CONTRIBUTOR_ROLE)
          ))
      ));

      try {
        raidApi.createRaidV1(createRequest);
        fail("No exception thrown with invalid orcid checksum");
      } catch (RaidApiValidationException e) {
        final var failures = e.getFailures();
        assertThat(failures).hasSize(1);
        assertThat(failures).contains(
          new ValidationFailure()
            .fieldId("contributors[0].id")
            .errorType("invalidValue")
            .message("failed checksum, last digit should be `1`")
        );
      } catch (Exception e) {
        fail("Expected RaidApiValidationException");
      }
    }

    @Test
    @DisplayName("Minting a RAiD with non-existent orcid fails")
    void nonExistentOrcid() {
      createRequest.setContributors(List.of(
        new Contributor()
          .identifierSchemeUri(CONTRIBUTOR_SCHEME_URI)
          .id("https://orcid.org/0000-0001-0000-0009")
          .positions(List.of(
            new ContribPosition()
              .startDate(LocalDate.now())
              .schemeUri(CONTRIBUTOR_POSITION_SCHEME_URI)
              .type(LEADER_POSITION)
          ))
          .roles(List.of(
            new ContribRole()
              .schemeUri(CONTRIBUTOR_ROLE_SCHEME_URI)
              .type(SOFTWARE_CONTRIBUTOR_ROLE)
          ))
      ));

      try {
        raidApi.createRaidV1(createRequest);
        fail("No exception thrown with non-existent orcid");
      } catch (RaidApiValidationException e) {
        final var failures = e.getFailures();
        assertThat(failures).hasSize(1);
        assertThat(failures).contains(
          new ValidationFailure()
            .fieldId("contributors[0].id")
            .errorType("invalidValue")
            .message("The contributor ORCID does not exist")
        );
      } catch (Exception e) {
        fail("Expected RaidApiValidationException");
      }
    }
  }
  @Test
  @DisplayName("Minting a RAiD with null positions fails")
  void nullPositions() {
    createRequest.setContributors(List.of(
      new Contributor()
        .identifierSchemeUri(CONTRIBUTOR_SCHEME_URI)
        .id("https://orcid.org/0000-0000-0000-0001")
        .roles(List.of(
          new ContribRole()
            .schemeUri(CONTRIBUTOR_ROLE_SCHEME_URI)
            .type(SOFTWARE_CONTRIBUTOR_ROLE)
        ))
    ));

    try {
      raidApi.createRaidV1(createRequest);
      fail("No exception thrown with null positions");
    } catch (RaidApiValidationException e) {
      final var failures = e.getFailures();
      assertThat(failures).hasSize(1);
      assertThat(failures).contains(
        new ValidationFailure()
          .fieldId("contributors.positions")
          .errorType("invalidValue")
          .message("leader must be specified")
      );
    } catch (Exception e) {
      fail("Expected RaidApiValidationException");
    }
  }

  @Test
  @DisplayName("Minting a RAiD with empty positions fails")
  void emptyPositions() {
    createRequest.setContributors(List.of(
      new Contributor()
        .identifierSchemeUri(CONTRIBUTOR_SCHEME_URI)
        .id("https://orcid.org/0000-0000-0000-0001")
        .positions(Collections.emptyList())
        .roles(List.of(
          new ContribRole()
            .schemeUri(CONTRIBUTOR_ROLE_SCHEME_URI)
            .type(SOFTWARE_CONTRIBUTOR_ROLE)
        ))
    ));

    try {
      raidApi.createRaidV1(createRequest);
      fail("No exception thrown with empty positions");
    } catch (RaidApiValidationException e) {
      final var failures = e.getFailures();
      assertThat(failures).hasSize(1);
      assertThat(failures).contains(
        new ValidationFailure()
          .fieldId("contributors.positions")
          .errorType("invalidValue")
          .message("leader must be specified")
      );
    } catch (Exception e) {
      fail("Expected RaidApiValidationException");
    }
  }

  @Test
  @DisplayName("Minting a RAiD with missing leader position fails")
  void missingLeader() {
    createRequest.setContributors(List.of(
      new Contributor()
        .identifierSchemeUri(CONTRIBUTOR_SCHEME_URI)
        .id("https://orcid.org/0000-0000-0000-0001")
        .positions(List.of(
          new ContribPosition()
            .startDate(LocalDate.now())
            .schemeUri(CONTRIBUTOR_POSITION_SCHEME_URI)
            .type(OTHER_PARTICIPANT_POSITION)
        ))        .roles(List.of(
          new ContribRole()
            .schemeUri(CONTRIBUTOR_ROLE_SCHEME_URI)
            .type(SOFTWARE_CONTRIBUTOR_ROLE)
        ))
    ));

    try {
      raidApi.createRaidV1(createRequest);
      fail("No exception thrown with missing leader position");
    } catch (RaidApiValidationException e) {
      final var failures = e.getFailures();
      assertThat(failures).hasSize(1);
      assertThat(failures).contains(
        new ValidationFailure()
          .fieldId("contributors.positions")
          .errorType("invalidValue")
          .message("leader must be specified")
      );
    } catch (Exception e) {
      fail("Expected RaidApiValidationException");
    }
  }

  @Nested
  @DisplayName("Position tests...")
  class ContributorPositionTests {
    @Test
    @DisplayName("Minting a RAiD with missing position schemeUri fails")
    void missingPositionSchemeUri() {
      createRequest.setContributors(List.of(
        new Contributor()
          .identifierSchemeUri(CONTRIBUTOR_SCHEME_URI)
          .id("https://orcid.org/0000-0000-0000-0001")
          .positions(List.of(
            new ContribPosition()
              .startDate(LocalDate.now())
              .type(LEADER_POSITION)
          ))        .roles(List.of(
            new ContribRole()
              .schemeUri(CONTRIBUTOR_ROLE_SCHEME_URI)
              .type(SOFTWARE_CONTRIBUTOR_ROLE)
          ))
      ));

      try {
        raidApi.createRaidV1(createRequest);
        fail("No exception thrown with missing contributor identifierSchemeUri");
      } catch (RaidApiValidationException e) {
        final var failures = e.getFailures();
        assertThat(failures).hasSize(1);
        assertThat(failures).contains(
          new ValidationFailure()
            .fieldId("contributors[0].positions[0].schemeUri")
            .errorType("notSet")
            .message("field must be set")
        );
      } catch (Exception e) {
        fail("Expected RaidApiValidationException");
      }
    }

    @Test
    @DisplayName("Minting a RAiD with missing position type fails")
    void missingPositionType() {
      createRequest.setContributors(List.of(
        new Contributor()
          .identifierSchemeUri(CONTRIBUTOR_SCHEME_URI)
          .id("https://orcid.org/0000-0000-0000-0001")
          .positions(List.of(
            new ContribPosition()
              .startDate(LocalDate.now())
              .schemeUri(CONTRIBUTOR_POSITION_SCHEME_URI)
              .type(LEADER_POSITION),
            new ContribPosition()
              .startDate(LocalDate.now())
              .schemeUri(CONTRIBUTOR_POSITION_SCHEME_URI)
          ))
          .roles(List.of(
            new ContribRole()
              .schemeUri(CONTRIBUTOR_ROLE_SCHEME_URI)
              .type(SOFTWARE_CONTRIBUTOR_ROLE)
          ))
      ));

      try {
        raidApi.createRaidV1(createRequest);
        fail("No exception thrown with missing contributor identifierSchemeUri");
      } catch (RaidApiValidationException e) {
        final var failures = e.getFailures();
        assertThat(failures).hasSize(1);
        assertThat(failures).contains(
          new ValidationFailure()
            .fieldId("contributors[0].positions[1].type")
            .errorType("notSet")
            .message("field must be set")
        );
      } catch (Exception e) {
        fail("Expected RaidApiValidationException");
      }
    }

    @Test
    @DisplayName("Minting a RAiD with invalid position schemeUri fails")
    void invalidPositionSchemeUri() {
      createRequest.setContributors(List.of(
        new Contributor()
          .identifierSchemeUri(CONTRIBUTOR_SCHEME_URI)
          .id("https://orcid.org/0000-0000-0000-0001")
          .positions(List.of(
            new ContribPosition()
              .startDate(LocalDate.now())
              .schemeUri(CONTRIBUTOR_POSITION_SCHEME_URI)
              .type(LEADER_POSITION),
            new ContribPosition()
              .startDate(LocalDate.now())
              .schemeUri("https://github.com/au-research/raid-metadata/tree/main/scheme/contributor/position/v2")
              .type(OTHER_PARTICIPANT_POSITION)
          ))
          .roles(List.of(
            new ContribRole()
              .schemeUri(CONTRIBUTOR_ROLE_SCHEME_URI)
              .type(SOFTWARE_CONTRIBUTOR_ROLE)
          ))
      ));

      try {
        raidApi.createRaidV1(createRequest);
        fail("No exception thrown with missing contributor identifierSchemeUri");
      } catch (RaidApiValidationException e) {
        final var failures = e.getFailures();
        assertThat(failures).hasSize(1);
        assertThat(failures).contains(
          new ValidationFailure()
            .fieldId("contributors[0].positions[1].schemeUri")
            .errorType("invalidValue")
            .message("has invalid/unsupported value")
        );
      } catch (Exception e) {
        fail("Expected RaidApiValidationException");
      }
    }

    @Test
    @DisplayName("Minting a RAiD with invalid position type for scheme fails")
    void invalidPositionTypeForScheme() {
      createRequest.setContributors(List.of(
        new Contributor()
          .identifierSchemeUri(CONTRIBUTOR_SCHEME_URI)
          .id("https://orcid.org/0000-0000-0000-0001")
          .positions(List.of(
            new ContribPosition()
              .startDate(LocalDate.now())
              .schemeUri(CONTRIBUTOR_POSITION_SCHEME_URI)
              .type(LEADER_POSITION),
            new ContribPosition()
              .startDate(LocalDate.now())
              .schemeUri(CONTRIBUTOR_POSITION_SCHEME_URI)
              .type("https://github.com/au-research/raid-metadata/blob/main/scheme/contributor/position/v1/unknown.json")
          ))
          .roles(List.of(
            new ContribRole()
              .schemeUri(CONTRIBUTOR_ROLE_SCHEME_URI)
              .type(SOFTWARE_CONTRIBUTOR_ROLE)
          ))
      ));

      try {
        raidApi.createRaidV1(createRequest);
        fail("No exception thrown with missing contributor identifierSchemeUri");
      } catch (RaidApiValidationException e) {
        final var failures = e.getFailures();
        assertThat(failures).hasSize(1);
        assertThat(failures).contains(
          new ValidationFailure()
            .fieldId("contributors[0].positions[1].type")
            .errorType("invalidValue")
            .message("has invalid/unsupported value")
        );
      } catch (Exception e) {
        fail("Expected RaidApiValidationException");
      }
    }
  }

  @Nested
  @DisplayName("Role tests...")
  class ContributorRoleTests {
    @Test
    @DisplayName("Minting a RAiD with missing role schemeUri fails")
    void missingRoleSchemeUri() {
      createRequest.setContributors(List.of(
        new Contributor()
          .identifierSchemeUri(CONTRIBUTOR_SCHEME_URI)
          .id("https://orcid.org/0000-0000-0000-0001")
          .positions(List.of(
            new ContribPosition()
              .schemeUri(CONTRIBUTOR_POSITION_SCHEME_URI)
              .startDate(LocalDate.now())
              .type(LEADER_POSITION)
          ))
          .roles(List.of(
            new ContribRole()
//              .schemeUri(CONTRIBUTOR_ROLE_SCHEME_URI)
              .type(SOFTWARE_CONTRIBUTOR_ROLE)
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
            .fieldId("contributors[0].roles[0].schemeUri")
            .errorType("notSet")
            .message("field must be set")
        );
      } catch (Exception e) {
        fail("Expected RaidApiValidationException");
      }
    }

    @Test
    @DisplayName("Minting a RAiD with missing role type fails")
    void missingPositionType() {
      createRequest.setContributors(List.of(
        new Contributor()
          .identifierSchemeUri(CONTRIBUTOR_SCHEME_URI)
          .id("https://orcid.org/0000-0000-0000-0001")
          .positions(List.of(
            new ContribPosition()
              .startDate(LocalDate.now())
              .schemeUri(CONTRIBUTOR_POSITION_SCHEME_URI)
              .type(LEADER_POSITION),
            new ContribPosition()
              .startDate(LocalDate.now())
              .schemeUri(CONTRIBUTOR_POSITION_SCHEME_URI)
              .type(OTHER_PARTICIPANT_POSITION)
          ))
          .roles(List.of(
            new ContribRole()
              .schemeUri(CONTRIBUTOR_ROLE_SCHEME_URI)
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
            .fieldId("contributors[0].roles[0].type")
            .errorType("notSet")
            .message("field must be set")
        );
      } catch (Exception e) {
        fail("Expected RaidApiValidationException");
      }
    }

    @Test
    @DisplayName("Minting a RAiD with invalid role schemeUri fails")
    void invalidPositionSchemeUri() {
      createRequest.setContributors(List.of(
        new Contributor()
          .identifierSchemeUri(CONTRIBUTOR_SCHEME_URI)
          .id("https://orcid.org/0000-0000-0000-0001")
          .positions(List.of(
            new ContribPosition()
              .startDate(LocalDate.now())
              .schemeUri(CONTRIBUTOR_POSITION_SCHEME_URI)
              .type(LEADER_POSITION),
            new ContribPosition()
              .startDate(LocalDate.now())
              .schemeUri(CONTRIBUTOR_POSITION_SCHEME_URI)
              .type(OTHER_PARTICIPANT_POSITION)
          ))
          .roles(List.of(
            new ContribRole()
              .schemeUri("unknown")
              .type(SOFTWARE_CONTRIBUTOR_ROLE)
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
            .fieldId("contributors[0].roles[0].schemeUri")
            .errorType("invalidValue")
            .message("has invalid/unsupported value")
        );
      } catch (Exception e) {
        fail("Expected RaidApiValidationException");
      }
    }

    @Test
    @DisplayName("Minting a RAiD with invalid type for role scheme fails")
    void invalidPositionTypeForScheme() {
      createRequest.setContributors(List.of(
        new Contributor()
          .identifierSchemeUri(CONTRIBUTOR_SCHEME_URI)
          .id("https://orcid.org/0000-0000-0000-0001")
          .positions(List.of(
            new ContribPosition()
              .startDate(LocalDate.now())
              .schemeUri(CONTRIBUTOR_POSITION_SCHEME_URI)
              .type(LEADER_POSITION),
            new ContribPosition()
              .startDate(LocalDate.now())
              .schemeUri(CONTRIBUTOR_POSITION_SCHEME_URI)
              .type(OTHER_PARTICIPANT_POSITION)
          ))
          .roles(List.of(
            new ContribRole()
              .schemeUri(CONTRIBUTOR_ROLE_SCHEME_URI)
              .type("unknown")
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
            .fieldId("contributors[0].roles[0].type")
            .errorType("invalidValue")
            .message("has invalid/unsupported value")
        );
      } catch (Exception e) {
        fail("Expected RaidApiValidationException");
      }
    }
  }
}