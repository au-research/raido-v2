/*
 * This file is generated by jOOQ.
 */
package raido.db.jooq.api_svc;


import org.jooq.ForeignKey;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;

import raido.db.jooq.api_svc.tables.AppUser;
import raido.db.jooq.api_svc.tables.FlywaySchemaHistory;
import raido.db.jooq.api_svc.tables.Raid;
import raido.db.jooq.api_svc.tables.RaidV2;
import raido.db.jooq.api_svc.tables.RaidoOperator;
import raido.db.jooq.api_svc.tables.ServicePoint;
import raido.db.jooq.api_svc.tables.UserAuthzRequest;
import raido.db.jooq.api_svc.tables.records.AppUserRecord;
import raido.db.jooq.api_svc.tables.records.FlywaySchemaHistoryRecord;
import raido.db.jooq.api_svc.tables.records.RaidRecord;
import raido.db.jooq.api_svc.tables.records.RaidV2Record;
import raido.db.jooq.api_svc.tables.records.RaidoOperatorRecord;
import raido.db.jooq.api_svc.tables.records.ServicePointRecord;
import raido.db.jooq.api_svc.tables.records.UserAuthzRequestRecord;


/**
 * A class modelling foreign key relationships and constraints of tables in
 * api_svc.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<AppUserRecord> APP_USER_PKEY = Internal.createUniqueKey(AppUser.APP_USER, DSL.name("app_user_pkey"), new TableField[] { AppUser.APP_USER.ID }, true);
    public static final UniqueKey<FlywaySchemaHistoryRecord> FLYWAY_SCHEMA_HISTORY_PK = Internal.createUniqueKey(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY, DSL.name("flyway_schema_history_pk"), new TableField[] { FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.INSTALLED_RANK }, true);
    public static final UniqueKey<RaidRecord> RAID_PKEY = Internal.createUniqueKey(Raid.RAID, DSL.name("raid_pkey"), new TableField[] { Raid.RAID.HANDLE }, true);
    public static final UniqueKey<RaidV2Record> RAID_V2_PKEY = Internal.createUniqueKey(RaidV2.RAID_V2, DSL.name("raid_v2_pkey"), new TableField[] { RaidV2.RAID_V2.HANDLE }, true);
    public static final UniqueKey<RaidoOperatorRecord> RAIDO_OPERATOR_PKEY = Internal.createUniqueKey(RaidoOperator.RAIDO_OPERATOR, DSL.name("raido_operator_pkey"), new TableField[] { RaidoOperator.RAIDO_OPERATOR.EMAIL }, true);
    public static final UniqueKey<ServicePointRecord> SERVICE_POINT_PKEY = Internal.createUniqueKey(ServicePoint.SERVICE_POINT, DSL.name("service_point_pkey"), new TableField[] { ServicePoint.SERVICE_POINT.ID }, true);
    public static final UniqueKey<ServicePointRecord> UNIQUE_NAME = Internal.createUniqueKey(ServicePoint.SERVICE_POINT, DSL.name("unique_name"), new TableField[] { ServicePoint.SERVICE_POINT.LOWER_NAME }, true);
    public static final UniqueKey<UserAuthzRequestRecord> USER_AUTHZ_REQUEST_PKEY = Internal.createUniqueKey(UserAuthzRequest.USER_AUTHZ_REQUEST, DSL.name("user_authz_request_pkey"), new TableField[] { UserAuthzRequest.USER_AUTHZ_REQUEST.ID }, true);

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<AppUserRecord, ServicePointRecord> APP_USER__APP_USER_SERVICE_POINT_ID_FKEY = Internal.createForeignKey(AppUser.APP_USER, DSL.name("app_user_service_point_id_fkey"), new TableField[] { AppUser.APP_USER.SERVICE_POINT_ID }, Keys.SERVICE_POINT_PKEY, new TableField[] { ServicePoint.SERVICE_POINT.ID }, true);
    public static final ForeignKey<RaidRecord, ServicePointRecord> RAID__RAID_SERVICE_POINT_ID_FKEY = Internal.createForeignKey(Raid.RAID, DSL.name("raid_service_point_id_fkey"), new TableField[] { Raid.RAID.SERVICE_POINT_ID }, Keys.SERVICE_POINT_PKEY, new TableField[] { ServicePoint.SERVICE_POINT.ID }, true);
    public static final ForeignKey<RaidV2Record, ServicePointRecord> RAID_V2__RAID_V2_SERVICE_POINT_ID_FKEY = Internal.createForeignKey(RaidV2.RAID_V2, DSL.name("raid_v2_service_point_id_fkey"), new TableField[] { RaidV2.RAID_V2.SERVICE_POINT_ID }, Keys.SERVICE_POINT_PKEY, new TableField[] { ServicePoint.SERVICE_POINT.ID }, true);
    public static final ForeignKey<UserAuthzRequestRecord, AppUserRecord> USER_AUTHZ_REQUEST__USER_AUTHZ_REQUEST_APPROVED_USER_FKEY = Internal.createForeignKey(UserAuthzRequest.USER_AUTHZ_REQUEST, DSL.name("user_authz_request_approved_user_fkey"), new TableField[] { UserAuthzRequest.USER_AUTHZ_REQUEST.APPROVED_USER }, Keys.APP_USER_PKEY, new TableField[] { AppUser.APP_USER.ID }, true);
    public static final ForeignKey<UserAuthzRequestRecord, AppUserRecord> USER_AUTHZ_REQUEST__USER_AUTHZ_REQUEST_RESPONDING_USER_FKEY = Internal.createForeignKey(UserAuthzRequest.USER_AUTHZ_REQUEST, DSL.name("user_authz_request_responding_user_fkey"), new TableField[] { UserAuthzRequest.USER_AUTHZ_REQUEST.RESPONDING_USER }, Keys.APP_USER_PKEY, new TableField[] { AppUser.APP_USER.ID }, true);
    public static final ForeignKey<UserAuthzRequestRecord, ServicePointRecord> USER_AUTHZ_REQUEST__USER_AUTHZ_REQUEST_SERVICE_POINT_ID_FKEY = Internal.createForeignKey(UserAuthzRequest.USER_AUTHZ_REQUEST, DSL.name("user_authz_request_service_point_id_fkey"), new TableField[] { UserAuthzRequest.USER_AUTHZ_REQUEST.SERVICE_POINT_ID }, Keys.SERVICE_POINT_PKEY, new TableField[] { ServicePoint.SERVICE_POINT.ID }, true);
}
