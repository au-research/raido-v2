/*
 * This file is generated by jOOQ.
 */
package au.org.raid.db.jooq.tables;


import au.org.raid.db.jooq.ApiSvc;
import au.org.raid.db.jooq.Keys;
import au.org.raid.db.jooq.tables.records.RaidRelatedObjectRecord;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function4;
import org.jooq.Identity;
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
public class RaidRelatedObject extends TableImpl<RaidRelatedObjectRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>api_svc.raid_related_object</code>
     */
    public static final RaidRelatedObject RAID_RELATED_OBJECT = new RaidRelatedObject();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<RaidRelatedObjectRecord> getRecordType() {
        return RaidRelatedObjectRecord.class;
    }

    /**
     * The column <code>api_svc.raid_related_object.id</code>.
     */
    public final TableField<RaidRelatedObjectRecord, Integer> ID = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>api_svc.raid_related_object.handle</code>.
     */
    public final TableField<RaidRelatedObjectRecord, String> HANDLE = createField(DSL.name("handle"), SQLDataType.VARCHAR.nullable(false), this, "");

    /**
     * The column <code>api_svc.raid_related_object.related_object_id</code>.
     */
    public final TableField<RaidRelatedObjectRecord, Integer> RELATED_OBJECT_ID = createField(DSL.name("related_object_id"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column
     * <code>api_svc.raid_related_object.related_object_type_id</code>.
     */
    public final TableField<RaidRelatedObjectRecord, Integer> RELATED_OBJECT_TYPE_ID = createField(DSL.name("related_object_type_id"), SQLDataType.INTEGER.nullable(false), this, "");

    private RaidRelatedObject(Name alias, Table<RaidRelatedObjectRecord> aliased) {
        this(alias, aliased, null);
    }

    private RaidRelatedObject(Name alias, Table<RaidRelatedObjectRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>api_svc.raid_related_object</code> table
     * reference
     */
    public RaidRelatedObject(String alias) {
        this(DSL.name(alias), RAID_RELATED_OBJECT);
    }

    /**
     * Create an aliased <code>api_svc.raid_related_object</code> table
     * reference
     */
    public RaidRelatedObject(Name alias) {
        this(alias, RAID_RELATED_OBJECT);
    }

    /**
     * Create a <code>api_svc.raid_related_object</code> table reference
     */
    public RaidRelatedObject() {
        this(DSL.name("raid_related_object"), null);
    }

    public <O extends Record> RaidRelatedObject(Table<O> child, ForeignKey<O, RaidRelatedObjectRecord> key) {
        super(child, key, RAID_RELATED_OBJECT);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : ApiSvc.API_SVC;
    }

    @Override
    public Identity<RaidRelatedObjectRecord, Integer> getIdentity() {
        return (Identity<RaidRelatedObjectRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<RaidRelatedObjectRecord> getPrimaryKey() {
        return Keys.RAID_RELATED_OBJECT_PKEY;
    }

    @Override
    public List<UniqueKey<RaidRelatedObjectRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.RAID_RELATED_OBJECT_HANDLE_RELATED_OBJECT_ID_RELATED_OBJECT_KEY);
    }

    @Override
    public List<ForeignKey<RaidRelatedObjectRecord, ?>> getReferences() {
        return Arrays.asList(Keys.RAID_RELATED_OBJECT__FK_RAID_RELATED_OBJECT_HANDLE, Keys.RAID_RELATED_OBJECT__FK_RAID_RELATED_OBJECT_RELATED_OBJECT_ID, Keys.RAID_RELATED_OBJECT__FK_RAID_RELATED_OBJECT_RELATED_OBJECT_TYPE_ID);
    }

    private transient Raid _raid;
    private transient RelatedObject _relatedObject;
    private transient RelatedObjectType _relatedObjectType;

    /**
     * Get the implicit join path to the <code>api_svc.raid</code> table.
     */
    public Raid raid() {
        if (_raid == null)
            _raid = new Raid(this, Keys.RAID_RELATED_OBJECT__FK_RAID_RELATED_OBJECT_HANDLE);

        return _raid;
    }

    /**
     * Get the implicit join path to the <code>api_svc.related_object</code>
     * table.
     */
    public RelatedObject relatedObject() {
        if (_relatedObject == null)
            _relatedObject = new RelatedObject(this, Keys.RAID_RELATED_OBJECT__FK_RAID_RELATED_OBJECT_RELATED_OBJECT_ID);

        return _relatedObject;
    }

    /**
     * Get the implicit join path to the
     * <code>api_svc.related_object_type</code> table.
     */
    public RelatedObjectType relatedObjectType() {
        if (_relatedObjectType == null)
            _relatedObjectType = new RelatedObjectType(this, Keys.RAID_RELATED_OBJECT__FK_RAID_RELATED_OBJECT_RELATED_OBJECT_TYPE_ID);

        return _relatedObjectType;
    }

    @Override
    public RaidRelatedObject as(String alias) {
        return new RaidRelatedObject(DSL.name(alias), this);
    }

    @Override
    public RaidRelatedObject as(Name alias) {
        return new RaidRelatedObject(alias, this);
    }

    @Override
    public RaidRelatedObject as(Table<?> alias) {
        return new RaidRelatedObject(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public RaidRelatedObject rename(String name) {
        return new RaidRelatedObject(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public RaidRelatedObject rename(Name name) {
        return new RaidRelatedObject(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public RaidRelatedObject rename(Table<?> name) {
        return new RaidRelatedObject(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<Integer, String, Integer, Integer> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function4<? super Integer, ? super String, ? super Integer, ? super Integer, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function4<? super Integer, ? super String, ? super Integer, ? super Integer, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
