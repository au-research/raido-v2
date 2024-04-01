/*
 * This file is generated by jOOQ.
 */
package au.org.raid.db.jooq.tables.records;


import au.org.raid.db.jooq.enums.SchemaStatus;
import au.org.raid.db.jooq.tables.TraditionalKnowledgeLabelSchema;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TraditionalKnowledgeLabelSchemaRecord extends UpdatableRecordImpl<TraditionalKnowledgeLabelSchemaRecord> implements Record3<Integer, String, SchemaStatus> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>api_svc.traditional_knowledge_label_schema.id</code>.
     */
    public TraditionalKnowledgeLabelSchemaRecord setId(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>api_svc.traditional_knowledge_label_schema.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>api_svc.traditional_knowledge_label_schema.uri</code>.
     */
    public TraditionalKnowledgeLabelSchemaRecord setUri(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>api_svc.traditional_knowledge_label_schema.uri</code>.
     */
    public String getUri() {
        return (String) get(1);
    }

    /**
     * Setter for
     * <code>api_svc.traditional_knowledge_label_schema.status</code>.
     */
    public TraditionalKnowledgeLabelSchemaRecord setStatus(SchemaStatus value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for
     * <code>api_svc.traditional_knowledge_label_schema.status</code>.
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
        return TraditionalKnowledgeLabelSchema.TRADITIONAL_KNOWLEDGE_LABEL_SCHEMA.ID;
    }

    @Override
    public Field<String> field2() {
        return TraditionalKnowledgeLabelSchema.TRADITIONAL_KNOWLEDGE_LABEL_SCHEMA.URI;
    }

    @Override
    public Field<SchemaStatus> field3() {
        return TraditionalKnowledgeLabelSchema.TRADITIONAL_KNOWLEDGE_LABEL_SCHEMA.STATUS;
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
    public TraditionalKnowledgeLabelSchemaRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public TraditionalKnowledgeLabelSchemaRecord value2(String value) {
        setUri(value);
        return this;
    }

    @Override
    public TraditionalKnowledgeLabelSchemaRecord value3(SchemaStatus value) {
        setStatus(value);
        return this;
    }

    @Override
    public TraditionalKnowledgeLabelSchemaRecord values(Integer value1, String value2, SchemaStatus value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TraditionalKnowledgeLabelSchemaRecord
     */
    public TraditionalKnowledgeLabelSchemaRecord() {
        super(TraditionalKnowledgeLabelSchema.TRADITIONAL_KNOWLEDGE_LABEL_SCHEMA);
    }

    /**
     * Create a detached, initialised TraditionalKnowledgeLabelSchemaRecord
     */
    public TraditionalKnowledgeLabelSchemaRecord(Integer id, String uri, SchemaStatus status) {
        super(TraditionalKnowledgeLabelSchema.TRADITIONAL_KNOWLEDGE_LABEL_SCHEMA);

        setId(id);
        setUri(uri);
        setStatus(status);
    }
}
