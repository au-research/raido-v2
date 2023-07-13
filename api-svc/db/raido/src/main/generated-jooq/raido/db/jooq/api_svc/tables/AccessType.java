/*
 * This file is generated by jOOQ.
 */
package raido.db.jooq.api_svc.tables;


import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function2;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row2;
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
import raido.db.jooq.api_svc.Keys;
import raido.db.jooq.api_svc.tables.records.AccessTypeRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AccessType extends TableImpl<AccessTypeRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>api_svc.access_type</code>
     */
    public static final AccessType ACCESS_TYPE = new AccessType();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AccessTypeRecord> getRecordType() {
        return AccessTypeRecord.class;
    }

    /**
     * The column <code>api_svc.access_type.scheme_id</code>.
     */
    public final TableField<AccessTypeRecord, Integer> SCHEME_ID = createField(DSL.name("scheme_id"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>api_svc.access_type.name</code>.
     */
    public final TableField<AccessTypeRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR.nullable(false), this, "");

    private AccessType(Name alias, Table<AccessTypeRecord> aliased) {
        this(alias, aliased, null);
    }

    private AccessType(Name alias, Table<AccessTypeRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>api_svc.access_type</code> table reference
     */
    public AccessType(String alias) {
        this(DSL.name(alias), ACCESS_TYPE);
    }

    /**
     * Create an aliased <code>api_svc.access_type</code> table reference
     */
    public AccessType(Name alias) {
        this(alias, ACCESS_TYPE);
    }

    /**
     * Create a <code>api_svc.access_type</code> table reference
     */
    public AccessType() {
        this(DSL.name("access_type"), null);
    }

    public <O extends Record> AccessType(Table<O> child, ForeignKey<O, AccessTypeRecord> key) {
        super(child, key, ACCESS_TYPE);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : ApiSvc.API_SVC;
    }

    @Override
    public UniqueKey<AccessTypeRecord> getPrimaryKey() {
        return Keys.ACCESS_TYPE_PKEY;
    }

    @Override
    public List<ForeignKey<AccessTypeRecord, ?>> getReferences() {
        return Arrays.asList(Keys.ACCESS_TYPE__FK_ACCESS_TYPE_SCHEME_ID);
    }

    private transient AccessTypeScheme _accessTypeScheme;

    /**
     * Get the implicit join path to the <code>api_svc.access_type_scheme</code>
     * table.
     */
    public AccessTypeScheme accessTypeScheme() {
        if (_accessTypeScheme == null)
            _accessTypeScheme = new AccessTypeScheme(this, Keys.ACCESS_TYPE__FK_ACCESS_TYPE_SCHEME_ID);

        return _accessTypeScheme;
    }

    @Override
    public AccessType as(String alias) {
        return new AccessType(DSL.name(alias), this);
    }

    @Override
    public AccessType as(Name alias) {
        return new AccessType(alias, this);
    }

    @Override
    public AccessType as(Table<?> alias) {
        return new AccessType(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public AccessType rename(String name) {
        return new AccessType(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public AccessType rename(Name name) {
        return new AccessType(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public AccessType rename(Table<?> name) {
        return new AccessType(name.getQualifiedName(), null);
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
