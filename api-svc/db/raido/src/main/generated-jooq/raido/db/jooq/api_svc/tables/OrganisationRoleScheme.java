/*
 * This file is generated by jOOQ.
 */
package raido.db.jooq.api_svc.tables;


import org.jooq.Record;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;
import raido.db.jooq.api_svc.ApiSvc;
import raido.db.jooq.api_svc.Keys;
import raido.db.jooq.api_svc.tables.records.OrganisationRoleSchemeRecord;

import java.util.function.Function;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class OrganisationRoleScheme extends TableImpl<OrganisationRoleSchemeRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>api_svc.organisation_role_scheme</code>
     */
    public static final OrganisationRoleScheme ORGANISATION_ROLE_SCHEME = new OrganisationRoleScheme();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<OrganisationRoleSchemeRecord> getRecordType() {
        return OrganisationRoleSchemeRecord.class;
    }

    /**
     * The column <code>api_svc.organisation_role_scheme.id</code>.
     */
    public final TableField<OrganisationRoleSchemeRecord, Integer> ID = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>api_svc.organisation_role_scheme.uri</code>.
     */
    public final TableField<OrganisationRoleSchemeRecord, String> URI = createField(DSL.name("uri"), SQLDataType.VARCHAR.nullable(false), this, "");

    private OrganisationRoleScheme(Name alias, Table<OrganisationRoleSchemeRecord> aliased) {
        this(alias, aliased, null);
    }

    private OrganisationRoleScheme(Name alias, Table<OrganisationRoleSchemeRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>api_svc.organisation_role_scheme</code> table
     * reference
     */
    public OrganisationRoleScheme(String alias) {
        this(DSL.name(alias), ORGANISATION_ROLE_SCHEME);
    }

    /**
     * Create an aliased <code>api_svc.organisation_role_scheme</code> table
     * reference
     */
    public OrganisationRoleScheme(Name alias) {
        this(alias, ORGANISATION_ROLE_SCHEME);
    }

    /**
     * Create a <code>api_svc.organisation_role_scheme</code> table reference
     */
    public OrganisationRoleScheme() {
        this(DSL.name("organisation_role_scheme"), null);
    }

    public <O extends Record> OrganisationRoleScheme(Table<O> child, ForeignKey<O, OrganisationRoleSchemeRecord> key) {
        super(child, key, ORGANISATION_ROLE_SCHEME);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : ApiSvc.API_SVC;
    }

    @Override
    public Identity<OrganisationRoleSchemeRecord, Integer> getIdentity() {
        return (Identity<OrganisationRoleSchemeRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<OrganisationRoleSchemeRecord> getPrimaryKey() {
        return Keys.ORGANISATION_ROLE_SCHEME_PKEY;
    }

    @Override
    public OrganisationRoleScheme as(String alias) {
        return new OrganisationRoleScheme(DSL.name(alias), this);
    }

    @Override
    public OrganisationRoleScheme as(Name alias) {
        return new OrganisationRoleScheme(alias, this);
    }

    @Override
    public OrganisationRoleScheme as(Table<?> alias) {
        return new OrganisationRoleScheme(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public OrganisationRoleScheme rename(String name) {
        return new OrganisationRoleScheme(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public OrganisationRoleScheme rename(Name name) {
        return new OrganisationRoleScheme(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public OrganisationRoleScheme rename(Table<?> name) {
        return new OrganisationRoleScheme(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row2<Integer, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function2<? super Integer, ? super String, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function2<? super Integer, ? super String, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
