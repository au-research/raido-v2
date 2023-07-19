package raido.apisvc.service.raid.validation;

import org.springframework.stereotype.Component;
import raido.idl.raidv2.model.Organisation;
import raido.idl.raidv2.model.ValidationFailure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static raido.apisvc.endpoint.message.ValidationMessage.*;
import static raido.apisvc.util.StringUtil.isBlank;

@Component
public class StableOrganisationValidationService {
  private static final String ROR_SCHEME_URI = "https://ror.org/";
  private final StableRorValidationService rorValidationService;
  private final StableOrganisationRoleValidationService roleValidationService;

  public StableOrganisationValidationService(final StableRorValidationService rorValidationService,
                                             final StableOrganisationRoleValidationService roleValidationService) {
    this.rorValidationService = rorValidationService;
    this.roleValidationService = roleValidationService;
  }

  public List<ValidationFailure> validate(
    List<Organisation> organisations
  ) {

    /* organisations has been confirmed as optional in the metadata schema,
    rationale: an ORCID is quick to create (minutes), RORs can take months. */
    if( organisations == null ) {
      return Collections.emptyList();
    }

    var failures = new ArrayList<ValidationFailure>();


    IntStream.range(0, organisations.size()).forEach(organisationIndex -> {
      final var organisation = organisations.get(organisationIndex);

      if (isBlank(organisation.getSchemeUri())) {
        failures.add(new ValidationFailure()
          .fieldId("organisations[%d].schemeUri".formatted(organisationIndex))
          .errorType(NOT_SET_TYPE)
          .message(FIELD_MUST_BE_SET_MESSAGE)
        );
      } else if (!organisation.getSchemeUri().equals(ROR_SCHEME_URI)) {
        failures.add(new ValidationFailure()
          .fieldId("organisations[%d].schemeUri")
          .errorType(INVALID_VALUE_TYPE)
          .message(INVALID_VALUE_MESSAGE)
        );
      }

      failures.addAll(rorValidationService.validate(organisation.getId(), organisationIndex));

      IntStream.range(0, organisation.getRoles().size()).forEach(roleIndex -> {
        final var role = organisation.getRoles().get(roleIndex);
        failures.addAll(roleValidationService.validate(role, organisationIndex, roleIndex));
      });
    });

    return failures;
  }
}

