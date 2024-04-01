/*
 * This file is generated by jOOQ.
 */
package au.org.raid.db.jooq.tables.records;


import au.org.raid.db.jooq.enums.SchemaStatus;
import au.org.raid.db.jooq.tables.TitleTypeSchema;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TitleTypeSchemaRecord extends UpdatableRecordImpl<TitleTypeSchemaRecord> implements Record3<Integer, String, SchemaStatus> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>api_svc.title_type_schema.id</code>.
     */
    public TitleTypeSchemaRecord setId(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>api_svc.title_type_schema.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>api_svc.title_type_schema.uri</code>.
     */
    public TitleTypeSchemaRecord setUri(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>api_svc.title_type_schema.uri</code>.
     */
    public String getUri() {
        return (String) get(1);
    }

    /**
     * Setter for <code>api_svc.title_type_schema.status</code>.
     */
    public TitleTypeSchemaRecord setStatus(SchemaStatus value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>api_svc.title_type_schema.status</code>.
     */
    public SchemaStatus getStatus() {
        return (SchemaStatus) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<Integer, String, SchemaStatus> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<Integer, String, SchemaStatus> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return TitleTypeSchema.TITLE_TYPE_SCHEMA.ID;
    }

    @Override
    public Field<String> field2() {
        return TitleTypeSchema.TITLE_TYPE_SCHEMA.URI;
    }

    @Override
    public Field<SchemaStatus> field3() {
        return TitleTypeSchema.TITLE_TYPE_SCHEMA.STATUS;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getUri();
    }

    @Override
    public SchemaStatus component3() {
        return getStatus();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getUri();
    }

    @Override
    public SchemaStatus value3() {
        return getStatus();
    }

    @Override
    public TitleTypeSchemaRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public TitleTypeSchemaRecord value2(String value) {
        setUri(value);
        return this;
    }

    @Override
    public TitleTypeSchemaRecord value3(SchemaStatus value) {
        setStatus(value);
        return this;
    }

    @Override
    public TitleTypeSchemaRecord values(Integer value1, String value2, SchemaStatus value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TitleTypeSchemaRecord
     */
    public TitleTypeSchemaRecord() {
        super(TitleTypeSchema.TITLE_TYPE_SCHEMA);
    }

    /**
     * Create a detached, initialised TitleTypeSchemaRecord
     */
    public TitleTypeSchemaRecord(Integer id, String uri, SchemaStatus status) {
        super(TitleTypeSchema.TITLE_TYPE_SCHEMA);

        setId(id);
        setUri(uri);
        setStatus(status);
    }
}
