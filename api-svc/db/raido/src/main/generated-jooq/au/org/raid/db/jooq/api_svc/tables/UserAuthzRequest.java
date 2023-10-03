/*
 * This file is generated by jOOQ.
 */
package au.org.raid.db.jooq.api_svc.tables;


import au.org.raid.db.jooq.api_svc.ApiSvc;
import au.org.raid.db.jooq.api_svc.Indexes;
import au.org.raid.db.jooq.api_svc.Keys;
import au.org.raid.db.jooq.api_svc.enums.AuthRequestStatus;
import au.org.raid.db.jooq.api_svc.enums.IdProvider;
import au.org.raid.db.jooq.api_svc.tables.records.UserAuthzRequestRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.jooq.Check;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function12;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row12;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UserAuthzRequest extends TableImpl<UserAuthzRequestRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>api_svc.user_authz_request</code>
     */
    public static final UserAuthzRequest USER_AUTHZ_REQUEST = new UserAuthzRequest();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<UserAuthzRequestRecord> getRecordType() {
        return UserAuthzRequestRecord.class;
    }

    /**
     * The column <code>api_svc.user_authz_request.id</code>.
     */
    public final TableField<UserAuthzRequestRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>api_svc.user_authz_request.status</code>.
     */
    public final TableField<UserAuthzRequestRecord, AuthRequestStatus> STATUS = createField(DSL.name("status"), SQLDataType.VARCHAR.nullable(false).asEnumDataType(au.org.raid.db.jooq.api_svc.enums.AuthRequestStatus.class), this, "");

    /**
     * The column <code>api_svc.user_authz_request.service_point_id</code>.
     */
    public final TableField<UserAuthzRequestRecord, Long> SERVICE_POINT_ID = createField(DSL.name("service_point_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>api_svc.user_authz_request.email</code>. Lowercase chars
     * only - db enforced
     */
    public final TableField<UserAuthzRequestRecord, String> EMAIL = createField(DSL.name("email"), SQLDataType.VARCHAR(256).nullable(false), this, "Lowercase chars only - db enforced");

    /**
     * The column <code>api_svc.user_authz_request.client_id</code>.
     */
    public final TableField<UserAuthzRequestRecord, String> CLIENT_ID = createField(DSL.name("client_id"), SQLDataType.VARCHAR(256).nullable(false), this, "");

    /**
     * The column <code>api_svc.user_authz_request.id_provider</code>.
     */
    public final TableField<UserAuthzRequestRecord, IdProvider> ID_PROVIDER = createField(DSL.name("id_provider"), SQLDataType.VARCHAR.nullable(false).asEnumDataType(au.org.raid.db.jooq.api_svc.enums.IdProvider.class), this, "");

    /**
     * The column <code>api_svc.user_authz_request.subject</code>.
     */
    public final TableField<UserAuthzRequestRecord, String> SUBJECT = createField(DSL.name("subject"), SQLDataType.VARCHAR(256).nullable(false), this, "");

    /**
     * The column <code>api_svc.user_authz_request.responding_user</code>. user
     * that approved or rejected, not set until that happens
     */
    public final TableField<UserAuthzRequestRecord, Long> RESPONDING_USER = createField(DSL.name("responding_user"), SQLDataType.BIGINT, this, "user that approved or rejected, not set until that happens");

    /**
     * The column <code>api_svc.user_authz_request.approved_user</code>. the
     * user that was approved, set when request is approved and the 
     *   user is created or updated
     */
    public final TableField<UserAuthzRequestRecord, Long> APPROVED_USER = createField(DSL.name("approved_user"), SQLDataType.BIGINT, this, "the user that was approved, set when request is approved and the \n  user is created or updated");

    /**
     * The column <code>api_svc.user_authz_request.description</code>.
     */
    public final TableField<UserAuthzRequestRecord, String> DESCRIPTION = createField(DSL.name("description"), SQLDataType.VARCHAR(1024).nullable(false), this, "");

    /**
     * The column <code>api_svc.user_authz_request.date_requested</code>.
     */
    public final TableField<UserAuthzRequestRecord, LocalDateTime> DATE_REQUESTED = createField(DSL.name("date_requested"), SQLDataType.LOCALDATETIME(6).defaultValue(DSL.field(DSL.raw("transaction_timestamp()"), SQLDataType.LOCALDATETIME)), this, "");

    /**
     * The column <code>api_svc.user_authz_request.date_responded</code>. not
     * set until approved or rejected
     */
    public final TableField<UserAuthzRequestRecord, LocalDateTime> DATE_RESPONDED = createField(DSL.name("date_responded"), SQLDataType.LOCALDATETIME(6), this, "not set until approved or rejected");

    private UserAuthzRequest(Name alias, Table<UserAuthzRequestRecord> aliased) {
        this(alias, aliased, null);
    }

    private UserAuthzRequest(Name alias, Table<UserAuthzRequestRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>api_svc.user_authz_request</code> table reference
     */
    public UserAuthzRequest(String alias) {
        this(DSL.name(alias), USER_AUTHZ_REQUEST);
    }

    /**
     * Create an aliased <code>api_svc.user_authz_request</code> table reference
     */
    public UserAuthzRequest(Name alias) {
        this(alias, USER_AUTHZ_REQUEST);
    }

    /**
     * Create a <code>api_svc.user_authz_request</code> table reference
     */
    public UserAuthzRequest() {
        this(DSL.name("user_authz_request"), null);
    }

    public <O extends Record> UserAuthzRequest(Table<O> child, ForeignKey<O, UserAuthzRequestRecord> key) {
        super(child, key, USER_AUTHZ_REQUEST);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : ApiSvc.API_SVC;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.USER_AUTHZ_REQUEST_ONCE_ACTIVE_KEY);
    }

    @Override
    public Identity<UserAuthzRequestRecord, Long> getIdentity() {
        return (Identity<UserAuthzRequestRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<UserAuthzRequestRecord> getPrimaryKey() {
        return Keys.USER_AUTHZ_REQUEST_PKEY;
    }

    @Override
    public List<ForeignKey<UserAuthzRequestRecord, ?>> getReferences() {
        return Arrays.asList(Keys.USER_AUTHZ_REQUEST__USER_AUTHZ_REQUEST_SERVICE_POINT_ID_FKEY, Keys.USER_AUTHZ_REQUEST__USER_AUTHZ_REQUEST_RESPONDING_USER_FKEY, Keys.USER_AUTHZ_REQUEST__USER_AUTHZ_REQUEST_APPROVED_USER_FKEY);
    }

    private transient ServicePoint _servicePoint;
    private transient AppUser _userAuthzRequestRespondingUserFkey;
    private transient AppUser _userAuthzRequestApprovedUserFkey;

    /**
     * Get the implicit join path to the <code>api_svc.service_point</code>
     * table.
     */
    public ServicePoint servicePoint() {
        if (_servicePoint == null)
            _servicePoint = new ServicePoint(this, Keys.USER_AUTHZ_REQUEST__USER_AUTHZ_REQUEST_SERVICE_POINT_ID_FKEY);

        return _servicePoint;
    }

    /**
     * Get the implicit join path to the <code>api_svc.app_user</code> table,
     * via the <code>user_authz_request_responding_user_fkey</code> key.
     */
    public AppUser userAuthzRequestRespondingUserFkey() {
        if (_userAuthzRequestRespondingUserFkey == null)
            _userAuthzRequestRespondingUserFkey = new AppUser(this, Keys.USER_AUTHZ_REQUEST__USER_AUTHZ_REQUEST_RESPONDING_USER_FKEY);

        return _userAuthzRequestRespondingUserFkey;
    }

    /**
     * Get the implicit join path to the <code>api_svc.app_user</code> table,
     * via the <code>user_authz_request_approved_user_fkey</code> key.
     */
    public AppUser userAuthzRequestApprovedUserFkey() {
        if (_userAuthzRequestApprovedUserFkey == null)
            _userAuthzRequestApprovedUserFkey = new AppUser(this, Keys.USER_AUTHZ_REQUEST__USER_AUTHZ_REQUEST_APPROVED_USER_FKEY);

        return _userAuthzRequestApprovedUserFkey;
    }

    @Override
    public List<Check<UserAuthzRequestRecord>> getChecks() {
        return Arrays.asList(
            Internal.createCheck(this, DSL.name("lowercase_email_constraint"), "(((email)::text = lower((email)::text)))", true)
        );
    }

    @Override
    public UserAuthzRequest as(String alias) {
        return new UserAuthzRequest(DSL.name(alias), this);
    }

    @Override
    public UserAuthzRequest as(Name alias) {
        return new UserAuthzRequest(alias, this);
    }

    @Override
    public UserAuthzRequest as(Table<?> alias) {
        return new UserAuthzRequest(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public UserAuthzRequest rename(String name) {
        return new UserAuthzRequest(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public UserAuthzRequest rename(Name name) {
        return new UserAuthzRequest(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public UserAuthzRequest rename(Table<?> name) {
        return new UserAuthzRequest(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row12 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row12<Long, AuthRequestStatus, Long, String, String, IdProvider, String, Long, Long, String, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row12) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function12<? super Long, ? super AuthRequestStatus, ? super Long, ? super String, ? super String, ? super IdProvider, ? super String, ? super Long, ? super Long, ? super String, ? super LocalDateTime, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function12<? super Long, ? super AuthRequestStatus, ? super Long, ? super String, ? super String, ? super IdProvider, ? super String, ? super Long, ? super Long, ? super String, ? super LocalDateTime, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
