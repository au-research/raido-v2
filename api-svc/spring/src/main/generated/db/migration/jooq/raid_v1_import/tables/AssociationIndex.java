/*
 * This file is generated by jOOQ.
 */
package db.migration.jooq.raid_v1_import.tables;


import db.migration.jooq.raid_v1_import.Keys;
import db.migration.jooq.raid_v1_import.RaidV1Import;
import db.migration.jooq.raid_v1_import.tables.records.AssociationIndexRecord;

import java.time.LocalDateTime;
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
 * imported from
 * arn:aws:dynamodb:ap-southeast-2:005299621378:table/RAiD-RAiDLiveDB-1SX7NYTSOSUKX-AssociationIndexTable-1EMNYHDPK9NBP
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AssociationIndex extends TableImpl<AssociationIndexRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>raid_v1_import.association_index</code>
     */
    public static final AssociationIndex ASSOCIATION_INDEX = new AssociationIndex();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AssociationIndexRecord> getRecordType() {
        return AssociationIndexRecord.class;
    }

    /**
     * The column <code>raid_v1_import.association_index.handle</code>.
     */
    public final TableField<AssociationIndexRecord, String> HANDLE = createField(DSL.name("handle"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>raid_v1_import.association_index.owner_name</code>.
     */
    public final TableField<AssociationIndexRecord, String> OWNER_NAME = createField(DSL.name("owner_name"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>raid_v1_import.association_index.raid_name</code>.
     */
    public final TableField<AssociationIndexRecord, String> RAID_NAME = createField(DSL.name("raid_name"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>raid_v1_import.association_index.role</code>. `owner` -
     * if type is `service`, otherwise not set
     */
    public final TableField<AssociationIndexRecord, String> ROLE = createField(DSL.name("role"), SQLDataType.CLOB.nullable(false), this, "`owner` - if type is `service`, otherwise not set");

    /**
     * The column <code>raid_v1_import.association_index.type</code>.
     * `service|institution` - 14K service, institution only 16
     */
    public final TableField<AssociationIndexRecord, String> TYPE = createField(DSL.name("type"), SQLDataType.CLOB.nullable(false), this, "`service|institution` - 14K service, institution only 16");

    /**
     * The column <code>raid_v1_import.association_index.start_date</code>.
     */
    public final TableField<AssociationIndexRecord, LocalDateTime> START_DATE = createField(DSL.name("start_date"), SQLDataType.LOCALDATETIME(6), this, "");

    /**
     * The column <code>raid_v1_import.association_index.s3_export</code>.
     */
    public final TableField<AssociationIndexRecord, JSONB> S3_EXPORT = createField(DSL.name("s3_export"), SQLDataType.JSONB.nullable(false), this, "");

    private AssociationIndex(Name alias, Table<AssociationIndexRecord> aliased) {
        this(alias, aliased, null);
    }

    private AssociationIndex(Name alias, Table<AssociationIndexRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("imported from arn:aws:dynamodb:ap-southeast-2:005299621378:table/RAiD-RAiDLiveDB-1SX7NYTSOSUKX-AssociationIndexTable-1EMNYHDPK9NBP"), TableOptions.table());
    }

    /**
     * Create an aliased <code>raid_v1_import.association_index</code> table
     * reference
     */
    public AssociationIndex(String alias) {
        this(DSL.name(alias), ASSOCIATION_INDEX);
    }

    /**
     * Create an aliased <code>raid_v1_import.association_index</code> table
     * reference
     */
    public AssociationIndex(Name alias) {
        this(alias, ASSOCIATION_INDEX);
    }

    /**
     * Create a <code>raid_v1_import.association_index</code> table reference
     */
    public AssociationIndex() {
        this(DSL.name("association_index"), null);
    }

    public <O extends Record> AssociationIndex(Table<O> child, ForeignKey<O, AssociationIndexRecord> key) {
        super(child, key, ASSOCIATION_INDEX);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : RaidV1Import.RAID_V1_IMPORT;
    }

    @Override
    public UniqueKey<AssociationIndexRecord> getPrimaryKey() {
        return Keys.ASSOCIATION_INDEX_PKEY;
    }

    @Override
    public AssociationIndex as(String alias) {
        return new AssociationIndex(DSL.name(alias), this);
    }

    @Override
    public AssociationIndex as(Name alias) {
        return new AssociationIndex(alias, this);
    }

    @Override
    public AssociationIndex as(Table<?> alias) {
        return new AssociationIndex(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public AssociationIndex rename(String name) {
        return new AssociationIndex(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public AssociationIndex rename(Name name) {
        return new AssociationIndex(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public AssociationIndex rename(Table<?> name) {
        return new AssociationIndex(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row7 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row7<String, String, String, String, String, LocalDateTime, JSONB> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function7<? super String, ? super String, ? super String, ? super String, ? super String, ? super LocalDateTime, ? super JSONB, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function7<? super String, ? super String, ? super String, ? super String, ? super String, ? super LocalDateTime, ? super JSONB, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
