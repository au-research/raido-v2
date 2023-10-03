/*
 * This file is generated by jOOQ.
 */
package au.org.raid.db.jooq.api_svc.tables.records;


import au.org.raid.db.jooq.api_svc.tables.TraditionalKnowledgeLabel;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TraditionalKnowledgeLabelRecord extends UpdatableRecordImpl<TraditionalKnowledgeLabelRecord> implements Record2<Integer, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>api_svc.traditional_knowledge_label.schema_id</code>.
     */
    public TraditionalKnowledgeLabelRecord setSchemaId(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>api_svc.traditional_knowledge_label.schema_id</code>.
     */
    public Integer getSchemaId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>api_svc.traditional_knowledge_label.uri</code>.
     */
    public TraditionalKnowledgeLabelRecord setUri(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>api_svc.traditional_knowledge_label.uri</code>.
     */
    public String getUri() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record2<Integer, String> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<Integer, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<Integer, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return TraditionalKnowledgeLabel.TRADITIONAL_KNOWLEDGE_LABEL.SCHEMA_ID;
    }

    @Override
    public Field<String> field2() {
        return TraditionalKnowledgeLabel.TRADITIONAL_KNOWLEDGE_LABEL.URI;
    }

    @Override
    public Integer component1() {
        return getSchemaId();
    }

    @Override
    public String component2() {
        return getUri();
    }

    @Override
    public Integer value1() {
        return getSchemaId();
    }

    @Override
    public String value2() {
        return getUri();
    }

    @Override
    public TraditionalKnowledgeLabelRecord value1(Integer value) {
        setSchemaId(value);
        return this;
    }

    @Override
    public TraditionalKnowledgeLabelRecord value2(String value) {
        setUri(value);
        return this;
    }

    @Override
    public TraditionalKnowledgeLabelRecord values(Integer value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TraditionalKnowledgeLabelRecord
     */
    public TraditionalKnowledgeLabelRecord() {
        super(TraditionalKnowledgeLabel.TRADITIONAL_KNOWLEDGE_LABEL);
    }

    /**
     * Create a detached, initialised TraditionalKnowledgeLabelRecord
     */
    public TraditionalKnowledgeLabelRecord(Integer schemaId, String uri) {
        super(TraditionalKnowledgeLabel.TRADITIONAL_KNOWLEDGE_LABEL);

        setSchemaId(schemaId);
        setUri(uri);
    }
}
