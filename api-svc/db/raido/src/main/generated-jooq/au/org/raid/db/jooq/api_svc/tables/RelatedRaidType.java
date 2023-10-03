/*
 * This file is generated by jOOQ.
 */
package au.org.raid.db.jooq.api_svc.tables;


import au.org.raid.db.jooq.api_svc.ApiSvc;
import au.org.raid.db.jooq.api_svc.Keys;
import au.org.raid.db.jooq.api_svc.tables.records.RelatedRaidTypeRecord;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function4;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row4;
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
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RelatedRaidType extends TableImpl<RelatedRaidTypeRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>api_svc.related_raid_type</code>
     */
    public static final RelatedRaidType RELATED_RAID_TYPE = new RelatedRaidType();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<RelatedRaidTypeRecord> getRecordType() {
        return RelatedRaidTypeRecord.class;
    }

    /**
     * The column <code>api_svc.related_raid_type.schema_id</code>.
     */
    public final TableField<RelatedRaidTypeRecord, Integer> SCHEMA_ID = createField(DSL.name("schema_id"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>api_svc.related_raid_type.uri</code>.
     */
    public final TableField<RelatedRaidTypeRecord, String> URI = createField(DSL.name("uri"), SQLDataType.VARCHAR.nullable(false), this, "");

    /**
     * The column <code>api_svc.related_raid_type.name</code>.
     */
    public final TableField<RelatedRaidTypeRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>api_svc.related_raid_type.description</code>.
     */
    public final TableField<RelatedRaidTypeRecord, String> DESCRIPTION = createField(DSL.name("description"), SQLDataType.VARCHAR, this, "");

    private RelatedRaidType(Name alias, Table<RelatedRaidTypeRecord> aliased) {
        this(alias, aliased, null);
    }

    private RelatedRaidType(Name alias, Table<RelatedRaidTypeRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>api_svc.related_raid_type</code> table reference
     */
    public RelatedRaidType(String alias) {
        this(DSL.name(alias), RELATED_RAID_TYPE);
    }

    /**
     * Create an aliased <code>api_svc.related_raid_type</code> table reference
     */
    public RelatedRaidType(Name alias) {
        this(alias, RELATED_RAID_TYPE);
    }

    /**
     * Create a <code>api_svc.related_raid_type</code> table reference
     */
    public RelatedRaidType() {
        this(DSL.name("related_raid_type"), null);
    }

    public <O extends Record> RelatedRaidType(Table<O> child, ForeignKey<O, RelatedRaidTypeRecord> key) {
        super(child, key, RELATED_RAID_TYPE);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : ApiSvc.API_SVC;
    }

    @Override
    public UniqueKey<RelatedRaidTypeRecord> getPrimaryKey() {
        return Keys.RELATED_RAID_TYPE_NEW_PKEY;
    }

    @Override
    public List<ForeignKey<RelatedRaidTypeRecord, ?>> getReferences() {
        return Arrays.asList(Keys.RELATED_RAID_TYPE__FK_RELATED_RAID_TYPE_SCHEMA_ID);
    }

    private transient RelatedRaidTypeSchema _relatedRaidTypeSchema;

    /**
     * Get the implicit join path to the
     * <code>api_svc.related_raid_type_schema</code> table.
     */
    public RelatedRaidTypeSchema relatedRaidTypeSchema() {
        if (_relatedRaidTypeSchema == null)
            _relatedRaidTypeSchema = new RelatedRaidTypeSchema(this, Keys.RELATED_RAID_TYPE__FK_RELATED_RAID_TYPE_SCHEMA_ID);

        return _relatedRaidTypeSchema;
    }

    @Override
    public RelatedRaidType as(String alias) {
        return new RelatedRaidType(DSL.name(alias), this);
    }

    @Override
    public RelatedRaidType as(Name alias) {
        return new RelatedRaidType(alias, this);
    }

    @Override
    public RelatedRaidType as(Table<?> alias) {
        return new RelatedRaidType(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public RelatedRaidType rename(String name) {
        return new RelatedRaidType(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public RelatedRaidType rename(Name name) {
        return new RelatedRaidType(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public RelatedRaidType rename(Table<?> name) {
        return new RelatedRaidType(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<Integer, String, String, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function4<? super Integer, ? super String, ? super String, ? super String, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function4<? super Integer, ? super String, ? super String, ? super String, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
