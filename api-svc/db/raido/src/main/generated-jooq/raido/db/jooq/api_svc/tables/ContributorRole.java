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
import raido.db.jooq.api_svc.tables.records.ContributorRoleRecord;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ContributorRole extends TableImpl<ContributorRoleRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>api_svc.contributor_role</code>
     */
    public static final ContributorRole CONTRIBUTOR_ROLE = new ContributorRole();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ContributorRoleRecord> getRecordType() {
        return ContributorRoleRecord.class;
    }

    /**
     * The column <code>api_svc.contributor_role.scheme_id</code>.
     */
    public final TableField<ContributorRoleRecord, Integer> SCHEME_ID = createField(DSL.name("scheme_id"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>api_svc.contributor_role.uri</code>.
     */
    public final TableField<ContributorRoleRecord, String> URI = createField(DSL.name("uri"), SQLDataType.VARCHAR.nullable(false), this, "");

    private ContributorRole(Name alias, Table<ContributorRoleRecord> aliased) {
        this(alias, aliased, null);
    }

    private ContributorRole(Name alias, Table<ContributorRoleRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>api_svc.contributor_role</code> table reference
     */
    public ContributorRole(String alias) {
        this(DSL.name(alias), CONTRIBUTOR_ROLE);
    }

    /**
     * Create an aliased <code>api_svc.contributor_role</code> table reference
     */
    public ContributorRole(Name alias) {
        this(alias, CONTRIBUTOR_ROLE);
    }

    /**
     * Create a <code>api_svc.contributor_role</code> table reference
     */
    public ContributorRole() {
        this(DSL.name("contributor_role"), null);
    }

    public <O extends Record> ContributorRole(Table<O> child, ForeignKey<O, ContributorRoleRecord> key) {
        super(child, key, CONTRIBUTOR_ROLE);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : ApiSvc.API_SVC;
    }

    @Override
    public UniqueKey<ContributorRoleRecord> getPrimaryKey() {
        return Keys.CONTRIBUTOR_ROLE_PKEY;
    }

    @Override
    public List<ForeignKey<ContributorRoleRecord, ?>> getReferences() {
        return Arrays.asList(Keys.CONTRIBUTOR_ROLE__FK_CONTRIBUTOR_ROLE_TYPE_SCHEME_ID);
    }

    private transient ContributorRoleScheme _contributorRoleScheme;

    /**
     * Get the implicit join path to the
     * <code>api_svc.contributor_role_scheme</code> table.
     */
    public ContributorRoleScheme contributorRoleScheme() {
        if (_contributorRoleScheme == null)
            _contributorRoleScheme = new ContributorRoleScheme(this, Keys.CONTRIBUTOR_ROLE__FK_CONTRIBUTOR_ROLE_TYPE_SCHEME_ID);

        return _contributorRoleScheme;
    }

    @Override
    public ContributorRole as(String alias) {
        return new ContributorRole(DSL.name(alias), this);
    }

    @Override
    public ContributorRole as(Name alias) {
        return new ContributorRole(alias, this);
    }

    @Override
    public ContributorRole as(Table<?> alias) {
        return new ContributorRole(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public ContributorRole rename(String name) {
        return new ContributorRole(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public ContributorRole rename(Name name) {
        return new ContributorRole(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public ContributorRole rename(Table<?> name) {
        return new ContributorRole(name.getQualifiedName(), null);
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
