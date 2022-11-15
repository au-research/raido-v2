package raido.apisvc.service.raid;

import org.jooq.DSLContext;
import org.jooq.JSONB;
import org.jooq.exception.NoDataFoundException;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import raido.apisvc.service.apids.ApidsService;
import raido.apisvc.service.raid.validation.RaidoSchemaV1ValidationService;
import raido.apisvc.util.Log;
import raido.db.jooq.api_svc.tables.records.RaidRecord;
import raido.db.jooq.api_svc.tables.records.ServicePointRecord;
import raido.idl.raidv2.model.AccessType;
import raido.idl.raidv2.model.MetadataSchemaV1;
import raido.idl.raidv2.model.ReadRaidResponseV2;
import raido.idl.raidv2.model.ValidationFailure;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.stream;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toMap;
import static org.springframework.transaction.annotation.Propagation.NEVER;
import static raido.apisvc.endpoint.message.ValidationMessage.SCHEMA_CHANGED;
import static raido.apisvc.service.raid.MetadataService.mapJs2Jq;
import static raido.apisvc.service.raid.RaidoSchemaV1Util.getPrimaryTitle;
import static raido.apisvc.service.raid.RaidoSchemaV1Util.getPrimaryTitles;
import static raido.apisvc.util.DateUtil.local2Offset;
import static raido.apisvc.util.DateUtil.offset2Local;
import static raido.apisvc.util.Log.to;
import static raido.apisvc.util.StringUtil.areEqual;
import static raido.db.jooq.api_svc.tables.Raid.RAID;
import static raido.db.jooq.api_svc.tables.ServicePoint.SERVICE_POINT;

@Component
public class RaidService {
  private static final Log log = to(RaidService.class);
  
  private DSLContext db;
  private ApidsService apidsSvc;
  private MetadataService metaSvc;
  private RaidoSchemaV1ValidationService validSvc;
  private TransactionTemplate tx;

  public RaidService(
    DSLContext db,
    ApidsService apidsSvc,
    MetadataService metaSvc,
    RaidoSchemaV1ValidationService validSvc,
    TransactionTemplate tx
  ) {
    this.db = db;
    this.apidsSvc = apidsSvc;
    this.metaSvc = metaSvc;
    this.validSvc = validSvc;
    this.tx = tx;
  }


  record DenormalisedRaidData(
    String primaryTitle,
    LocalDate startDate,
    boolean confidential
  ) { }

  /** Expects the passed metadata is valid. */
  public DenormalisedRaidData getDenormalisedRaidData(
    MetadataSchemaV1 metadata
  ){
    return new DenormalisedRaidData(
      getPrimaryTitle(metadata.getTitles()).getTitle(),
      metadata.getDates().getStartDate(),
      metadata.getAccess().getType() != AccessType.OPEN
    );
  }

  /**
   Note propagation=NEVER causes this to fail if called when a TX is 
   currently active. The method does manual TX handling internally, 
   to avoid holding an open TX while talking to the APIDS service.
   */
  @Transactional(propagation = NEVER)
  public String mintRaidoSchemaV1(
    long servicePointId, 
    MetadataSchemaV1 metadata
  ) throws ValidationFailureException {
    /* this is the part where we want to make sure no TX is help open.
    * Maybe *this* should be marked tx.prop=never? */
    var response = apidsSvc.mintApidsHandleContentPrefix(
      metaSvc::formatRaidoLandingPageUrl);
    String handle = response.identifier.handle;
    String raidUrl = response.identifier.property.value;

    metadata.setId(metaSvc.createIdBlock(handle, raidUrl));

    // validation failure possible
    String metadataAsJson = metaSvc.mapToJson(metadata);
    var raidData = getDenormalisedRaidData(metadata);

    tx.executeWithoutResult((status)->{
      db.insertInto(RAID).
        set(RAID.HANDLE, handle).
        set(RAID.SERVICE_POINT_ID, servicePointId).
        set(RAID.URL, raidUrl).
        set(RAID.URL_INDEX, response.identifier.property.index).
        set(RAID.PRIMARY_TITLE, raidData.primaryTitle()).
        set(RAID.METADATA, JSONB.valueOf(metadataAsJson)).
        set(RAID.METADATA_SCHEMA, mapJs2Jq(metadata.getMetadataSchema())).
        set(RAID.START_DATE, raidData.startDate()).
        set(RAID.DATE_CREATED, LocalDateTime.now()).
        set(RAID.CONFIDENTIAL, raidData.confidential()).
        execute();
    });
    
    return handle;
  }

  public record ReadRaidV2Data(
    RaidRecord raid, 
    ServicePointRecord servicePoint
  ){}

  public ReadRaidV2Data readRaidV2Data(String handle){
    return db.select(RAID.fields()).
      select(SERVICE_POINT.fields()).
      from(RAID).join(SERVICE_POINT).onKey().
      where(RAID.HANDLE.eq(handle)).
      fetchSingle(r -> new ReadRaidV2Data(
        r.into(RaidRecord.class), 
        r.into(ServicePointRecord.class)) );
  }
  
  public ReadRaidResponseV2 readRaidResponseV2(String handle){
    ReadRaidV2Data data = null;
    try {
      data = readRaidV2Data(handle);
    }
    catch( NoDataFoundException e ){
      /* want to easily see what handles are failing, without having to 
      turn on param logging for all endpoints. When we implement selective 
      enablement of param logging at filter level, can get rid of this. */
      log.with("handle", handle).warn(e.getMessage());
      throw new RuntimeException(e);
    }
    return new ReadRaidResponseV2().
      handle(data.raid().getHandle()).
      servicePointId(data.servicePoint().getId()).
      servicePointName(data.servicePoint().getName()).
      primaryTitle(data.raid().getPrimaryTitle()).
      startDate(data.raid().getStartDate()).
      createDate(local2Offset(data.raid().getDateCreated())).
      url(data.raid().getUrl()).
      metadata(data.raid().getMetadata().data());
  }

  public void migrateRaidoSchemaV1(
    long servicePointId,
    int urlContentIndex,
    OffsetDateTime createDate, 
    MetadataSchemaV1 metadata
  ) throws ValidationFailureException {
    String primaryTitle = getPrimaryTitles(metadata.getTitles()).
      get(0).getTitle();
    LocalDate startDate = metadata.getDates().getStartDate();
    boolean confidential = metadata.getAccess().getType() != AccessType.OPEN;

    // migrated raids already have handles
    String handle = metadata.getId().getIdentifier();
    String raidUrl = metaSvc.formatRaidoLandingPageUrl(handle);

    metadata.getId().setRaidAgencyUrl(raidUrl);
    metadata.getId().setRaidAgencyIdentifier(
      metaSvc.getMetaProps().raidAgencyIdentifier );
    
    // validation failure possible
    String metadataAsJson = metaSvc.mapToJson(metadata);

    JSONB jsonbMetadata = JSONB.valueOf(metadataAsJson);

    db.insertInto(RAID).
      set(RAID.HANDLE, handle).
      set(RAID.SERVICE_POINT_ID, servicePointId).
      set(RAID.URL, raidUrl).
      set(RAID.URL_INDEX, urlContentIndex).
      set(RAID.PRIMARY_TITLE, primaryTitle).
      set(RAID.METADATA, jsonbMetadata).
      set(RAID.METADATA_SCHEMA, mapJs2Jq(metadata.getMetadataSchema())).
      set(RAID.START_DATE, startDate).
      set(RAID.DATE_CREATED, offset2Local(createDate)).
      set(RAID.CONFIDENTIAL, confidential).
      onConflict(RAID.HANDLE).doUpdate().
        set(stream(RAID.fields()).collect(toMap(f -> f, DSL::excluded))).
        where(RAID.HANDLE.eq(handle)).
      execute();
  }

  /* improve: after it's working, try to factor this so that validation can be
   separated out and called from the endpoint and this is just "do work".
   Might not be possible though, think validation is too intertwined with the
   work that this method actually does. */
  public List<ValidationFailure> updateRaidoSchemaV1(
    MetadataSchemaV1 newData,
    RaidRecord oldRaid
  ) {

    if( !areEqual(
      newData.getMetadataSchema().getValue(), 
      oldRaid.getMetadataSchema().getLiteral()) 
    ){
      return singletonList(SCHEMA_CHANGED);
    }
    
    var oldData = metaSvc.mapV1SchemaMetadata(oldRaid);

    List<ValidationFailure> failures = new ArrayList<>();
    
    failures.addAll(
      validSvc.validateIdBlockNotChanged(newData.getId(), oldData.getId()) );
    failures.addAll(validSvc.validateRaidoSchemaV1(newData));

    // validation failure possible (conversion error or maxSize of json)
    String metadataAsJson = null;
    try {
      metadataAsJson = metaSvc.mapToJson(newData);
    }
    catch( ValidationFailureException e ){
      failures.addAll(e.getFailures());
    }
    
    if( !failures.isEmpty() ){
      return failures;
    }

    var raidData = getDenormalisedRaidData(newData);

    db.update(RAID).
      set(RAID.PRIMARY_TITLE, raidData.primaryTitle()).
      set(RAID.METADATA, JSONB.valueOf(metadataAsJson)).
      set(RAID.START_DATE, raidData.startDate()).
      set(RAID.CONFIDENTIAL, raidData.confidential()).
      where(RAID.HANDLE.eq(oldRaid.getHandle())).
      execute();
    
    return emptyList();
  }

  public ServicePointRecord findServicePoint(String name){
    return db.select().from(SERVICE_POINT).
      where(SERVICE_POINT.NAME.eq(name)).
      fetchSingleInto(SERVICE_POINT);
  }
}
