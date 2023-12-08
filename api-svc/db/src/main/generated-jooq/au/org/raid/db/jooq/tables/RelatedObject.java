/*
 * This file is generated by jOOQ.
 */
package au.org.raid.db.jooq.tables;


import au.org.raid.db.jooq.ApiSvc;
import au.org.raid.db.jooq.Keys;
import au.org.raid.db.jooq.tables.records.RelatedObjectRecord;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function3;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row3;
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
public class RelatedObject extends TableImpl<RelatedObjectRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>api_svc.related_object</code>
     */
    public static final RelatedObject RELATED_OBJECT = new RelatedObject();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<RelatedObjectRecord> getRecordType() {
        return RelatedObjectRecord.class;
    }

    /**
     * The column <code>api_svc.related_object.id</code>.
     */
    public final TableField<RelatedObjectRecord, Integer> ID = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>api_svc.related_object.pid</code>.
     */
    public final TableField<RelatedObjectRecord, String> PID = createField(DSL.name("pid"), SQLDataType.VARCHAR.nullable(false), this, "");

    /**
     * The column <code>api_svc.related_object.schema_id</code>.
     */
    public final TableField<RelatedObjectRecord, Integer> SCHEMA_ID = createField(DSL.name("schema_id"), SQLDataType.INTEGER.nullable(false), this, "");

    private RelatedObject(Name alias, Table<RelatedObjectRecord> aliased) {
        this(alias, aliased, null);
    }

    private RelatedObject(Name alias, Table<RelatedObjectRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>api_svc.related_object</code> table reference
     */
    public RelatedObject(String alias) {
        this(DSL.name(alias), RELATED_OBJECT);
    }

    /**
     * Create an aliased <code>api_svc.related_object</code> table reference
     */
    public RelatedObject(Name alias) {
        this(alias, RELATED_OBJECT);
    }

    /**
     * Create a <code>api_svc.related_object</code> table reference
     */
    public RelatedObject() {
        this(DSL.name("related_object"), null);
    }

    public <O extends Record> RelatedObject(Table<O> child, ForeignKey<O, RelatedObjectRecord> key) {
        super(child, key, RELATED_OBJECT);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : ApiSvc.API_SVC;
    }

    @Override
    public Identity<RelatedObjectRecord, Integer> getIdentity() {
        return (Identity<RelatedObjectRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<RelatedObjectRecord> getPrimaryKey() {
        return Keys.RELATED_OBJECT_PKEY;
    }

    @Override
    public List<UniqueKey<RelatedObjectRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.RELATED_OBJECT_PID_SCHEMA_ID_KEY);
    }

    @Override
    public List<ForeignKey<RelatedObjectRecord, ?>> getReferences() {
        return Arrays.asList(Keys.RELATED_OBJECT__FK_RELATED_OBJECT_SCHEMA_ID);
    }

    private transient RelatedObjectSchema _relatedObjectSchema;

    /**
     * Get the implicit join path to the
     * <code>api_svc.related_object_schema</code> table.
     */
    public RelatedObjectSchema relatedObjectSchema() {
        if (_relatedObjectSchema == null)
            _relatedObjectSchema = new RelatedObjectSchema(this, Keys.RELATED_OBJECT__FK_RELATED_OBJECT_SCHEMA_ID);

        return _relatedObjectSchema;
    }

    @Override
    public RelatedObject as(String alias) {
        return new RelatedObject(DSL.name(alias), this);
    }

    @Override
    public RelatedObject as(Name alias) {
        return new RelatedObject(alias, this);
    }

    @Override
    public RelatedObject as(Table<?> alias) {
        return new RelatedObject(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public RelatedObject rename(String name) {
        return new RelatedObject(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public RelatedObject rename(Name name) {
        return new RelatedObject(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public RelatedObject rename(Table<?> name) {
        return new RelatedObject(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<Integer, String, Integer> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function3<? super Integer, ? super String, ? super Integer, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function3<? super Integer, ? super String, ? super Integer, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
