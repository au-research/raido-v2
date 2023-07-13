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
import raido.db.jooq.api_svc.tables.records.DescriptionTypeRecord;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DescriptionType extends TableImpl<DescriptionTypeRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>api_svc.description_type</code>
     */
    public static final DescriptionType DESCRIPTION_TYPE = new DescriptionType();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DescriptionTypeRecord> getRecordType() {
        return DescriptionTypeRecord.class;
    }

    /**
     * The column <code>api_svc.description_type.scheme_id</code>.
     */
    public final TableField<DescriptionTypeRecord, Integer> SCHEME_ID = createField(DSL.name("scheme_id"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>api_svc.description_type.name</code>.
     */
    public final TableField<DescriptionTypeRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR.nullable(false), this, "");

    private DescriptionType(Name alias, Table<DescriptionTypeRecord> aliased) {
        this(alias, aliased, null);
    }

    private DescriptionType(Name alias, Table<DescriptionTypeRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>api_svc.description_type</code> table reference
     */
    public DescriptionType(String alias) {
        this(DSL.name(alias), DESCRIPTION_TYPE);
    }

    /**
     * Create an aliased <code>api_svc.description_type</code> table reference
     */
    public DescriptionType(Name alias) {
        this(alias, DESCRIPTION_TYPE);
    }

    /**
     * Create a <code>api_svc.description_type</code> table reference
     */
    public DescriptionType() {
        this(DSL.name("description_type"), null);
    }

    public <O extends Record> DescriptionType(Table<O> child, ForeignKey<O, DescriptionTypeRecord> key) {
        super(child, key, DESCRIPTION_TYPE);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : ApiSvc.API_SVC;
    }

    @Override
    public UniqueKey<DescriptionTypeRecord> getPrimaryKey() {
        return Keys.DESCRIPTION_TYPE_PKEY;
    }

    @Override
    public List<ForeignKey<DescriptionTypeRecord, ?>> getReferences() {
        return Arrays.asList(Keys.DESCRIPTION_TYPE__FK_DESCRIPTION_TYPE_SCHEME_ID);
    }

    private transient DescriptionTypeScheme _descriptionTypeScheme;

    /**
     * Get the implicit join path to the
     * <code>api_svc.description_type_scheme</code> table.
     */
    public DescriptionTypeScheme descriptionTypeScheme() {
        if (_descriptionTypeScheme == null)
            _descriptionTypeScheme = new DescriptionTypeScheme(this, Keys.DESCRIPTION_TYPE__FK_DESCRIPTION_TYPE_SCHEME_ID);

        return _descriptionTypeScheme;
    }

    @Override
    public DescriptionType as(String alias) {
        return new DescriptionType(DSL.name(alias), this);
    }

    @Override
    public DescriptionType as(Name alias) {
        return new DescriptionType(alias, this);
    }

    @Override
    public DescriptionType as(Table<?> alias) {
        return new DescriptionType(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public DescriptionType rename(String name) {
        return new DescriptionType(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public DescriptionType rename(Name name) {
        return new DescriptionType(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public DescriptionType rename(Table<?> name) {
        return new DescriptionType(name.getQualifiedName(), null);
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
