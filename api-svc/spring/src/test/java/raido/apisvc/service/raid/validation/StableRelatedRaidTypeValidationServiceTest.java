package raido.apisvc.service.raid.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import raido.apisvc.repository.RelatedRaidTypeRepository;
import raido.apisvc.repository.RelatedRaidTypeSchemeRepository;
import raido.db.jooq.api_svc.tables.records.RelatedRaidTypeRecord;
import raido.db.jooq.api_svc.tables.records.RelatedRaidTypeSchemeRecord;
import raido.idl.raidv2.model.RelatedRaidType;
import raido.idl.raidv2.model.ValidationFailure;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static raido.apisvc.util.TestConstants.RELATED_RAID_TYPE_SCHEME_URI;
import static raido.apisvc.util.TestConstants.CONTINUES_RELATED_RAID_TYPE;

@ExtendWith(MockitoExtension.class)
class StableRelatedRaidTypeValidationServiceTest {
  private static final int INDEX = 3;
  private static final int RELATED_RAID_TYPE_SCHEME_ID = 1;

  private static final RelatedRaidTypeSchemeRecord RELATED_RAID_TYPE_SCHEME_RECORD = new RelatedRaidTypeSchemeRecord()
    .setId(RELATED_RAID_TYPE_SCHEME_ID)
    .setUri(RELATED_RAID_TYPE_SCHEME_URI);

  private static final RelatedRaidTypeRecord RELATED_RAID_TYPE_RECORD = new RelatedRaidTypeRecord()
    .setSchemeId(RELATED_RAID_TYPE_SCHEME_ID)
    .setUri(CONTINUES_RELATED_RAID_TYPE);

  @Mock
  private RelatedRaidTypeSchemeRepository relatedRaidTypeSchemeRepository;
  @Mock
  private RelatedRaidTypeRepository relatedRaidTypeRepository;
  @InjectMocks
  private StableRelatedRaidTypeValidationService validationService;

  @Test
  @DisplayName("Validation passes with valid related raid type")
  void validRelatedRaidType() {
    final var relatedRaidType = new RelatedRaidType()
      .id(CONTINUES_RELATED_RAID_TYPE)
      .schemeUri(RELATED_RAID_TYPE_SCHEME_URI);

    when(relatedRaidTypeSchemeRepository.findByUri(RELATED_RAID_TYPE_SCHEME_URI))
      .thenReturn(Optional.of(RELATED_RAID_TYPE_SCHEME_RECORD));
    when(relatedRaidTypeRepository.findByUriAndSchemeId(CONTINUES_RELATED_RAID_TYPE, RELATED_RAID_TYPE_SCHEME_ID))
      .thenReturn(Optional.of(RELATED_RAID_TYPE_RECORD));

    final var failures = validationService.validate(relatedRaidType, INDEX);

    assertThat(failures, empty());

    verify(relatedRaidTypeSchemeRepository).findByUri(RELATED_RAID_TYPE_SCHEME_URI);
    verify(relatedRaidTypeRepository).findByUriAndSchemeId(CONTINUES_RELATED_RAID_TYPE, RELATED_RAID_TYPE_SCHEME_ID);
  }

  @Test
  @DisplayName("Validation fails when id is null")
  void nullId() {
    final var relatedRaidType = new RelatedRaidType()
      .schemeUri(RELATED_RAID_TYPE_SCHEME_URI);

    when(relatedRaidTypeSchemeRepository.findByUri(RELATED_RAID_TYPE_SCHEME_URI))
      .thenReturn(Optional.of(RELATED_RAID_TYPE_SCHEME_RECORD));

    final var failures = validationService.validate(relatedRaidType, INDEX);

    assertThat(failures, hasSize(1));
    assertThat(failures, hasItem(
      new ValidationFailure()
        .fieldId("relatedRaids[3].type.id")
        .errorType("notSet")
        .message("field must be set")
    ));
  }

  @Test
  @DisplayName("Validation fails when id is empty string")
  void emptyId() {
    final var relatedRaidType = new RelatedRaidType()
      .id("")
      .schemeUri(RELATED_RAID_TYPE_SCHEME_URI);

    when(relatedRaidTypeSchemeRepository.findByUri(RELATED_RAID_TYPE_SCHEME_URI))
      .thenReturn(Optional.of(RELATED_RAID_TYPE_SCHEME_RECORD));

    final var failures = validationService.validate(relatedRaidType, INDEX);

    assertThat(failures, hasSize(1));
    assertThat(failures, hasItem(
      new ValidationFailure()
        .fieldId("relatedRaids[3].type.id")
        .errorType("notSet")
        .message("field must be set")
    ));
  }

  @Test
  @DisplayName("Validation fails when schemeUri is null")
  void nullSchemeUri() {
    final var relatedRaidType = new RelatedRaidType()
      .id(CONTINUES_RELATED_RAID_TYPE);

    final var failures = validationService.validate(relatedRaidType, INDEX);

    assertThat(failures, hasSize(1));
    assertThat(failures, hasItem(
      new ValidationFailure()
        .fieldId("relatedRaids[3].type.schemeUri")
        .errorType("notSet")
        .message("field must be set")
    ));
  }

  @Test
  @DisplayName("Validation fails when schemeUri is empty")
  void emptySchemeUri() {
    final var relatedRaidType = new RelatedRaidType()
      .id(CONTINUES_RELATED_RAID_TYPE)
      .schemeUri("");

    final var failures = validationService.validate(relatedRaidType, INDEX);

    assertThat(failures, hasSize(1));
    assertThat(failures, hasItem(
      new ValidationFailure()
        .fieldId("relatedRaids[3].type.schemeUri")
        .errorType("notSet")
        .message("field must be set")
    ));
  }

  @Test
  @DisplayName("Validation fails when schemeUri is invalid")
  void invalidSchemeUri() {
    final var relatedRaidType = new RelatedRaidType()
      .id(CONTINUES_RELATED_RAID_TYPE)
      .schemeUri(RELATED_RAID_TYPE_SCHEME_URI);

    when(relatedRaidTypeSchemeRepository.findByUri(RELATED_RAID_TYPE_SCHEME_URI))
      .thenReturn(Optional.empty());

    final var failures = validationService.validate(relatedRaidType, INDEX);

    assertThat(failures, hasSize(1));
    assertThat(failures, hasItem(
      new ValidationFailure()
        .fieldId("relatedRaids[3].type.schemeUri")
        .errorType("invalidValue")
        .message("scheme is unknown/unsupported")
    ));
  }

  @Test
  @DisplayName("Validation fails when type is null")
  void nullType() {
    final var failures = validationService.validate(null, INDEX);

    assertThat(failures, hasSize(1));
    assertThat(failures, hasItem(
      new ValidationFailure()
        .fieldId("relatedRaids[3].type")
        .errorType("notSet")
        .message("field must be set")
    ));

    verifyNoInteractions(relatedRaidTypeSchemeRepository);
    verifyNoInteractions(relatedRaidTypeRepository);
  }

  @Test
  @DisplayName("Validation fails when id not found in scheme")
  void invalidTypeForScheme() {
    final var relatedRaidType = new RelatedRaidType()
      .id(CONTINUES_RELATED_RAID_TYPE)
      .schemeUri(RELATED_RAID_TYPE_SCHEME_URI);

    when(relatedRaidTypeSchemeRepository.findByUri(RELATED_RAID_TYPE_SCHEME_URI))
      .thenReturn(Optional.of(RELATED_RAID_TYPE_SCHEME_RECORD));

    when(relatedRaidTypeRepository.findByUriAndSchemeId(CONTINUES_RELATED_RAID_TYPE, RELATED_RAID_TYPE_SCHEME_ID))
      .thenReturn(Optional.empty());

    final var failures = validationService.validate(relatedRaidType, INDEX);

    assertThat(failures, hasSize(1));
    assertThat(failures, hasItem(
      new ValidationFailure()
        .fieldId("relatedRaids[3].type.id")
        .errorType("invalidValue")
        .message("id does not exist within the given scheme")
    ));
  }
}