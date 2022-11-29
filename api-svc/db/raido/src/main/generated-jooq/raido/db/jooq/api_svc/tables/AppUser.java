/*
 * This file is generated by jOOQ.
 */
package raido.db.jooq.api_svc.tables;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function10;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row10;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import raido.db.jooq.api_svc.ApiSvc;
import raido.db.jooq.api_svc.Indexes;
import raido.db.jooq.api_svc.Keys;
import raido.db.jooq.api_svc.enums.IdProvider;
import raido.db.jooq.api_svc.enums.UserRole;
import raido.db.jooq.api_svc.tables.records.AppUserRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AppUser extends TableImpl<AppUserRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>api_svc.app_user</code>
     */
    public static final AppUser APP_USER = new AppUser();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AppUserRecord> getRecordType() {
        return AppUserRecord.class;
    }

    /**
     * The column <code>api_svc.app_user.id</code>.
     */
    public final TableField<AppUserRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>api_svc.app_user.service_point_id</code>.
     */
    public final TableField<AppUserRecord, Long> SERVICE_POINT_ID = createField(DSL.name("service_point_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>api_svc.app_user.email</code>. should be renamed to
     * "description" or some such.  api-keys do not and orcid 
     *   sign-ins might not have email address
     */
    public final TableField<AppUserRecord, String> EMAIL = createField(DSL.name("email"), SQLDataType.VARCHAR(256).nullable(false), this, "should be renamed to \"description\" or some such.  api-keys do not and orcid \r\n  sign-ins might not have email address");

    /**
     * The column <code>api_svc.app_user.client_id</code>.
     */
    public final TableField<AppUserRecord, String> CLIENT_ID = createField(DSL.name("client_id"), SQLDataType.VARCHAR(256).nullable(false), this, "");

    /**
     * The column <code>api_svc.app_user.subject</code>.
     */
    public final TableField<AppUserRecord, String> SUBJECT = createField(DSL.name("subject"), SQLDataType.VARCHAR(256).nullable(false), this, "");

    /**
     * The column <code>api_svc.app_user.id_provider</code>. not a real identity
     * field, its just redundant info we figure it out from 
     *   the clientId or issuer and store it for easy analysis
     */
    public final TableField<AppUserRecord, IdProvider> ID_PROVIDER = createField(DSL.name("id_provider"), SQLDataType.VARCHAR.nullable(false).asEnumDataType(raido.db.jooq.api_svc.enums.IdProvider.class), this, "not a real identity field, its just redundant info we figure it out from \r\n  the clientId or issuer and store it for easy analysis");

    /**
     * The column <code>api_svc.app_user.role</code>.
     */
    public final TableField<AppUserRecord, UserRole> ROLE = createField(DSL.name("role"), SQLDataType.VARCHAR.nullable(false).asEnumDataType(raido.db.jooq.api_svc.enums.UserRole.class), this, "");

    /**
     * The column <code>api_svc.app_user.enabled</code>.
     */
    public final TableField<AppUserRecord, Boolean> ENABLED = createField(DSL.name("enabled"), SQLDataType.BOOLEAN.nullable(false).defaultValue(DSL.field("true", SQLDataType.BOOLEAN)), this, "");

    /**
     * The column <code>api_svc.app_user.token_cutoff</code>. Any endpoint call
     * with a bearer token issued after this point will be 
     *   rejected. Any authentication attempt after this point will be rejected.
     */
    public final TableField<AppUserRecord, LocalDateTime> TOKEN_CUTOFF = createField(DSL.name("token_cutoff"), SQLDataType.LOCALDATETIME(6), this, "Any endpoint call with a bearer token issued after this point will be \r\n  rejected. Any authentication attempt after this point will be rejected.");

    /**
     * The column <code>api_svc.app_user.date_created</code>.
     */
    public final TableField<AppUserRecord, LocalDateTime> DATE_CREATED = createField(DSL.name("date_created"), SQLDataType.LOCALDATETIME(6).nullable(false).defaultValue(DSL.field("transaction_timestamp()", SQLDataType.LOCALDATETIME)), this, "");

    private AppUser(Name alias, Table<AppUserRecord> aliased) {
        this(alias, aliased, null);
    }

    private AppUser(Name alias, Table<AppUserRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>api_svc.app_user</code> table reference
     */
    public AppUser(String alias) {
        this(DSL.name(alias), APP_USER);
    }

    /**
     * Create an aliased <code>api_svc.app_user</code> table reference
     */
    public AppUser(Name alias) {
        this(alias, APP_USER);
    }

    /**
     * Create a <code>api_svc.app_user</code> table reference
     */
    public AppUser() {
        this(DSL.name("app_user"), null);
    }

    public <O extends Record> AppUser(Table<O> child, ForeignKey<O, AppUserRecord> key) {
        super(child, key, APP_USER);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : ApiSvc.API_SVC;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.APP_USER_ID_FIELDS_ACTIVE_KEY);
    }

    @Override
    public Identity<AppUserRecord, Long> getIdentity() {
        return (Identity<AppUserRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<AppUserRecord> getPrimaryKey() {
        return Keys.APP_USER_PKEY;
    }

    @Override
    public List<ForeignKey<AppUserRecord, ?>> getReferences() {
        return Arrays.asList(Keys.APP_USER__APP_USER_SERVICE_POINT_ID_FKEY);
    }

    private transient ServicePoint _servicePoint;

    /**
     * Get the implicit join path to the <code>api_svc.service_point</code>
     * table.
     */
    public ServicePoint servicePoint() {
        if (_servicePoint == null)
            _servicePoint = new ServicePoint(this, Keys.APP_USER__APP_USER_SERVICE_POINT_ID_FKEY);

        return _servicePoint;
    }

    @Override
    public AppUser as(String alias) {
        return new AppUser(DSL.name(alias), this);
    }

    @Override
    public AppUser as(Name alias) {
        return new AppUser(alias, this);
    }

    @Override
    public AppUser as(Table<?> alias) {
        return new AppUser(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public AppUser rename(String name) {
        return new AppUser(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public AppUser rename(Name name) {
        return new AppUser(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public AppUser rename(Table<?> name) {
        return new AppUser(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row10 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row10<Long, Long, String, String, String, IdProvider, UserRole, Boolean, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row10) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function10<? super Long, ? super Long, ? super String, ? super String, ? super String, ? super IdProvider, ? super UserRole, ? super Boolean, ? super LocalDateTime, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function10<? super Long, ? super Long, ? super String, ? super String, ? super String, ? super IdProvider, ? super UserRole, ? super Boolean, ? super LocalDateTime, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
