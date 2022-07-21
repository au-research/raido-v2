/*
 * This file is generated by jOOQ.
 */
package db.migration.jooq.tables;


import db.migration.jooq.Keys;
import db.migration.jooq.RaidV1Import;
import db.migration.jooq.tables.records.MetadataRecord;

import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function7;
import org.jooq.JSONB;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row7;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * from
 * arn:aws:dynamodb:ap-southeast-2:005299621378:table/RAiD-MetadataTable-5X1IHWPICN82
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Metadata extends TableImpl<MetadataRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>raid_v1_import.metadata</code>
     */
    public static final Metadata METADATA = new Metadata();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<MetadataRecord> getRecordType() {
        return MetadataRecord.class;
    }

    /**
     * The column <code>raid_v1_import.metadata.name</code>.
     */
    public final TableField<MetadataRecord, String> NAME = createField(DSL.name("name"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>raid_v1_import.metadata.type</code>.
     */
    public final TableField<MetadataRecord, String> TYPE = createField(DSL.name("type"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>raid_v1_import.metadata.grid</code>.
     */
    public final TableField<MetadataRecord, String> GRID = createField(DSL.name("grid"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>raid_v1_import.metadata.isni</code>.
     */
    public final TableField<MetadataRecord, String> ISNI = createField(DSL.name("isni"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>raid_v1_import.metadata.admin_email</code>.
     */
    public final TableField<MetadataRecord, String> ADMIN_EMAIL = createField(DSL.name("admin_email"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>raid_v1_import.metadata.tech_email</code>.
     */
    public final TableField<MetadataRecord, String> TECH_EMAIL = createField(DSL.name("tech_email"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>raid_v1_import.metadata.s3_export</code>.
     */
    public final TableField<MetadataRecord, JSONB> S3_EXPORT = createField(DSL.name("s3_export"), SQLDataType.JSONB.nullable(false), this, "");

    private Metadata(Name alias, Table<MetadataRecord> aliased) {
        this(alias, aliased, null);
    }

    private Metadata(Name alias, Table<MetadataRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("from arn:aws:dynamodb:ap-southeast-2:005299621378:table/RAiD-MetadataTable-5X1IHWPICN82"), TableOptions.table());
    }

    /**
     * Create an aliased <code>raid_v1_import.metadata</code> table reference
     */
    public Metadata(String alias) {
        this(DSL.name(alias), METADATA);
    }

    /**
     * Create an aliased <code>raid_v1_import.metadata</code> table reference
     */
    public Metadata(Name alias) {
        this(alias, METADATA);
    }

    /**
     * Create a <code>raid_v1_import.metadata</code> table reference
     */
    public Metadata() {
        this(DSL.name("metadata"), null);
    }

    public <O extends Record> Metadata(Table<O> child, ForeignKey<O, MetadataRecord> key) {
        super(child, key, METADATA);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : RaidV1Import.RAID_V1_IMPORT;
    }

    @Override
    public UniqueKey<MetadataRecord> getPrimaryKey() {
        return Keys.METADATA_PKEY;
    }

    @Override
    public Metadata as(String alias) {
        return new Metadata(DSL.name(alias), this);
    }

    @Override
    public Metadata as(Name alias) {
        return new Metadata(alias, this);
    }

    @Override
    public Metadata as(Table<?> alias) {
        return new Metadata(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Metadata rename(String name) {
        return new Metadata(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Metadata rename(Name name) {
        return new Metadata(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Metadata rename(Table<?> name) {
        return new Metadata(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row7 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row7<String, String, String, String, String, String, JSONB> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function7<? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super JSONB, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function7<? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super JSONB, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
