/*
 * This file is generated by jOOQ.
 */
package au.org.raid.db.jooq.api_svc.tables;


import au.org.raid.db.jooq.api_svc.ApiSvc;
import au.org.raid.db.jooq.api_svc.Keys;
import au.org.raid.db.jooq.api_svc.tables.records.SubjectTypeRecord;
import org.jooq.Record;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SubjectType extends TableImpl<SubjectTypeRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>api_svc.subject_type</code>
     */
    public static final SubjectType SUBJECT_TYPE = new SubjectType();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<SubjectTypeRecord> getRecordType() {
        return SubjectTypeRecord.class;
    }

    /**
     * The column <code>api_svc.subject_type.id</code>.
     */
    public final TableField<SubjectTypeRecord, String> ID = createField(DSL.name("id"), SQLDataType.VARCHAR(6).nullable(false), this, "");

    /**
     * The column <code>api_svc.subject_type.name</code>.
     */
    public final TableField<SubjectTypeRecord, String> NAME = createField(DSL.name("name"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>api_svc.subject_type.description</code>.
     */
    public final TableField<SubjectTypeRecord, String> DESCRIPTION = createField(DSL.name("description"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>api_svc.subject_type.note</code>.
     */
    public final TableField<SubjectTypeRecord, String> NOTE = createField(DSL.name("note"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>api_svc.subject_type.schema_id</code>.
     */
    public final TableField<SubjectTypeRecord, Integer> SCHEMA_ID = createField(DSL.name("schema_id"), SQLDataType.INTEGER, this, "");

    private SubjectType(Name alias, Table<SubjectTypeRecord> aliased) {
        this(alias, aliased, null);
    }

    private SubjectType(Name alias, Table<SubjectTypeRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>api_svc.subject_type</code> table reference
     */
    public SubjectType(String alias) {
        this(DSL.name(alias), SUBJECT_TYPE);
    }

    /**
     * Create an aliased <code>api_svc.subject_type</code> table reference
     */
    public SubjectType(Name alias) {
        this(alias, SUBJECT_TYPE);
    }

    /**
     * Create a <code>api_svc.subject_type</code> table reference
     */
    public SubjectType() {
        this(DSL.name("subject_type"), null);
    }

    public <O extends Record> SubjectType(Table<O> child, ForeignKey<O, SubjectTypeRecord> key) {
        super(child, key, SUBJECT_TYPE);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : ApiSvc.API_SVC;
    }

    @Override
    public UniqueKey<SubjectTypeRecord> getPrimaryKey() {
        return Keys.SUBJECT_PKEY;
    }

    @Override
    public List<ForeignKey<SubjectTypeRecord, ?>> getReferences() {
        return Arrays.asList(Keys.SUBJECT_TYPE__FK_SUBJECT_TYPE_SCHEMA_ID);
    }

    private transient SubjectTypeSchema _subjectTypeSchema;

    /**
     * Get the implicit join path to the
     * <code>api_svc.subject_type_schema</code> table.
     */
    public SubjectTypeSchema subjectTypeSchema() {
        if (_subjectTypeSchema == null)
            _subjectTypeSchema = new SubjectTypeSchema(this, Keys.SUBJECT_TYPE__FK_SUBJECT_TYPE_SCHEMA_ID);

        return _subjectTypeSchema;
    }

    @Override
    public SubjectType as(String alias) {
        return new SubjectType(DSL.name(alias), this);
    }

    @Override
    public SubjectType as(Name alias) {
        return new SubjectType(alias, this);
    }

    @Override
    public SubjectType as(Table<?> alias) {
        return new SubjectType(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public SubjectType rename(String name) {
        return new SubjectType(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public SubjectType rename(Name name) {
        return new SubjectType(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public SubjectType rename(Table<?> name) {
        return new SubjectType(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row5 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row5<String, String, String, String, Integer> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function5<? super String, ? super String, ? super String, ? super String, ? super Integer, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function5<? super String, ? super String, ? super String, ? super String, ? super Integer, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
