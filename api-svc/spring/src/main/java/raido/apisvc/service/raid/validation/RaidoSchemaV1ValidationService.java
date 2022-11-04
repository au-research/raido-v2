package raido.apisvc.service.raid.validation;

import org.springframework.stereotype.Component;
import raido.apisvc.endpoint.message.ValidationMessage;
import raido.apisvc.util.Log;
import raido.idl.raidv2.model.AccessBlock;
import raido.idl.raidv2.model.AccessType;
import raido.idl.raidv2.model.DatesBlock;
import raido.idl.raidv2.model.IdBlock;
import raido.idl.raidv2.model.MetadataSchemaV1;
import raido.idl.raidv2.model.Metaschema;
import raido.idl.raidv2.model.ValidationFailure;

import java.util.ArrayList;
import java.util.List;

import static java.util.List.of;
import static raido.apisvc.endpoint.raidv2.PublicExperimental.HANDLE_SEPERATOR;
import static raido.apisvc.service.raid.MetadataService.RAID_ID_TYPE_URI;
import static raido.apisvc.util.Log.to;
import static raido.apisvc.util.ObjectUtil.areEqual;
import static raido.apisvc.util.StringUtil.isBlank;

@Component
public class RaidoSchemaV1ValidationService {
  private static final Log log = to(RaidoSchemaV1ValidationService.class);

  private TitleValidationService titleSvc;
  private DescriptionValidationService descSvc;

  public RaidoSchemaV1ValidationService(
    TitleValidationService titleSvc,
    DescriptionValidationService descSvc
  ) {
    this.titleSvc = titleSvc;
    this.descSvc = descSvc;
  }

  /**
   Does not currently validate the ID block.
   */
  public List<ValidationFailure> validateRaidoSchemaV1(
    MetadataSchemaV1 metadata
  ) {
    if( metadata == null ) {
      return of(ValidationMessage.METADATA_NOT_SET);
    }

    var failures = new ArrayList<ValidationFailure>();
    if( metadata.getMetadataSchema() != Metaschema.RAIDO_METADATA_SCHEMA_V1 ) {
      failures.add(ValidationMessage.INVALID_METADATA_SCHEMA);
    }

    failures.addAll(validateDates(metadata.getDates()));
    failures.addAll(validateAccess(metadata.getAccess()));
    failures.addAll(titleSvc.validateTitles(metadata.getTitles()));
    failures.addAll(descSvc.validateDescriptions(metadata.getDescriptions()));

    return failures;
  }

  private static List<ValidationFailure> validateDates(
    DatesBlock dates
  ) {
    var failures = new ArrayList<ValidationFailure>();
    if( dates == null ) {
      failures.add(ValidationMessage.DATES_NOT_SET);
    }
    else {
      if( dates.getStartDate() == null ){
        failures.add(ValidationMessage.DATES_START_DATE_NOT_SET);
      }
    }
    return failures;
  }

  private static List<ValidationFailure> validateAccess(
    AccessBlock access
  ) {
    var failures = new ArrayList<ValidationFailure>();
    
    if( access == null ) {
      failures.add(ValidationMessage.ACCESS_NOT_SET);
    }
    else {
      if( access.getType() == null ){
        failures.add(ValidationMessage.ACCESS_TYPE_NOT_SET);
      }
      else {
        if( 
          access.getType() == AccessType.CLOSED && 
            access.getAccessStatement() == null 
        ){
          failures.add(ValidationMessage.ACCESS_STATEMENT_NOT_SET);
        }
      }
    }
    
    return failures;
  }


  public List<ValidationFailure> validateIdBlockForMigration(IdBlock id){
    var failures = new ArrayList<ValidationFailure>();
    if( id == null ){
      failures.add(ValidationMessage.ID_NOT_SET);
      return failures;
    }
    
    if( isBlank(id.getIdentifier()) ){
      failures.add(ValidationMessage.IDENTIFIER_NOT_SET);
    }
    else {
      var handleSplit = id.getIdentifier().split(HANDLE_SEPERATOR);
      if( handleSplit.length == 2 ){
        if( isBlank(handleSplit[0]) ){
          log.with("handle", id.getIdentifier()).
            warn("handle prefix was blank");
          failures.add(ValidationMessage.IDENTIFIER_INVALID);
        }
        if( isBlank(handleSplit[1]) ){
          log.with("handle", id.getIdentifier()).
            warn("handle suffix was blank");
          failures.add(ValidationMessage.IDENTIFIER_INVALID);
        }
      }
      else {
        log.with("handle", id.getIdentifier()).with("split", handleSplit).
          warn("attempted to import invalid handle");
        failures.add(ValidationMessage.IDENTIFIER_INVALID);
      }
    }
    
    // improve: would be good to validate the handle format
    // at least that prefix and suffix exist, separated by slash
    
    if( !areEqual(id.getIdentifierTypeUri(), RAID_ID_TYPE_URI) ){
      failures.add(ValidationMessage.ID_TYPE_URI_INVALID);
    }

    if( isBlank(id.getGlobalUrl()) ){
      failures.add(ValidationMessage.GLOBAL_URL_NOT_SET);
    }

    // don't need client to send raidAgency fields, we'll set them on insert
    
    return failures;
  }
}
