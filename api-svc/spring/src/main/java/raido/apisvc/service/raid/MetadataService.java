package raido.apisvc.service.raid;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;
import raido.apisvc.util.Guard;
import raido.apisvc.util.Log;
import raido.idl.raidv2.model.AccessType;
import raido.idl.raidv2.model.RaidoMetadataSchemaV1;
import raido.idl.raidv2.model.TitleBlock;
import raido.idl.raidv2.model.TitleType;

import java.util.List;

import static com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES;
import static raido.apisvc.util.ExceptionUtil.iae;
import static raido.apisvc.util.Guard.failedCheck;
import static raido.apisvc.util.Log.to;
import static raido.apisvc.util.StringUtil.areEqual;

@Component
public class MetadataService {
  private static final Log log = to(MetadataService.class);

  private ObjectMapper defaultMapper = defaultMapper();

  public enum Schema {
    RAIDO_V1("raido-metadata-schema-v1");

    private final String id;

    Schema(String id) {
      this.id = id;
    }

    public String getId() {
      return id;
    }
  }

  public Schema mapSchema(String schema){
    if( areEqual(schema, Schema.RAIDO_V1.getId()) ){
      return Schema.RAIDO_V1;
    }
    
    throw iae("unrecognised shema: %s", schema);
  }

  public String mapToJson(Object metadataInstance){
    String jsonValue = null;
    try {
      jsonValue = defaultMapper.writeValueAsString(metadataInstance);
    }
    catch( JsonProcessingException e ){
      log.with("metadata", metadataInstance).error(e.getMessage());
      throw new RuntimeException(e);
    }
    
    return jsonValue;
  }

  public static ObjectMapper defaultMapper() {
    return new ObjectMapper().
      /* copied from the default mapper in ApiConfig, 
      I don't want number timestamps in the DB either.
      Not sure if this should be bean instead of just a field. */
        disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).
      /* without this, converting the metadata to json string gave: 
      "Java 8 date/time type `java.time.LocalDate` not supported by default" */
        registerModule(new JavaTimeModule()).
      enable(ALLOW_UNQUOTED_FIELD_NAMES).
      // fields where value is null or Optional will not be written 
        setSerializationInclusion(JsonInclude.Include.NON_ABSENT);
  }

  // temporary, this is more of a "guard" method than a "validate"
  public void validateRaidoSchemaV1(
    String schema, RaidoMetadataSchemaV1 metadata
  ) {
    Guard.areEqual(schema, Schema.RAIDO_V1.getId());
    Guard.notNull("metadata field must be set", metadata);
    Guard.notNull("metadata field must be set", metadata);
    Guard.notNull("dates field must be set",
      metadata.getDates());
    Guard.notNull("dates.startDate field must be set",
      metadata.getDates().getStartDate());

    Guard.notNull("access field must be set", metadata.getAccess());
    Guard.notNull("access.type field myst be set",
      metadata.getAccess().getType());
    if( metadata.getAccess().getType() == AccessType.CLOSED ){
      Guard.hasValue("accessStatement field must be set if type is closed",
        metadata.getAccess().getAccessStatement());
    }
  }

  public String validateHasPrimaryTitle(List<TitleBlock> titles) {
    Guard.notNull("titles field must be set", titles);

    var primaryTitles = titles.stream().
      filter(i->i.getType() == TitleType.PRIMARY_TITLE).toList();

    if( primaryTitles.size() != 1 ){
      throw failedCheck("titles field must have exactly one primary title");
    }
    Guard.hasValue("primaryTitle should have a value",
      primaryTitles.get(0).getTitle() );

    return primaryTitles.get(0).getTitle();
  }
  
}
