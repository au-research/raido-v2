/*
 * This file is generated by jOOQ.
 */
package au.org.raid.db.jooq.api_svc.tables;


import au.org.raid.db.jooq.api_svc.ApiSvc;
import au.org.raid.db.jooq.api_svc.Keys;
import au.org.raid.db.jooq.api_svc.tables.records.TraditionalKnowledgeLabelRecord;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function2;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row2;
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
public class TraditionalKnowledgeLabel extends TableImpl<TraditionalKnowledgeLabelRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of
     * <code>api_svc.traditional_knowledge_label</code>
     */
    public static final TraditionalKnowledgeLabel TRADITIONAL_KNOWLEDGE_LABEL = new TraditionalKnowledgeLabel();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TraditionalKnowledgeLabelRecord> getRecordType() {
        return TraditionalKnowledgeLabelRecord.class;
    }

    /**
     * The column <code>api_svc.traditional_knowledge_label.schema_id</code>.
     */
    public final TableField<TraditionalKnowledgeLabelRecord, Integer> SCHEMA_ID = createField(DSL.name("schema_id"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>api_svc.traditional_knowledge_label.uri</code>.
     */
    public final TableField<TraditionalKnowledgeLabelRecord, String> URI = createField(DSL.name("uri"), SQLDataType.VARCHAR.nullable(false), this, "");

    private TraditionalKnowledgeLabel(Name alias, Table<TraditionalKnowledgeLabelRecord> aliased) {
        this(alias, aliased, null);
    }

    private TraditionalKnowledgeLabel(Name alias, Table<TraditionalKnowledgeLabelRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>api_svc.traditional_knowledge_label</code> table
     * reference
     */
    public TraditionalKnowledgeLabel(String alias) {
        this(DSL.name(alias), TRADITIONAL_KNOWLEDGE_LABEL);
    }

    /**
     * Create an aliased <code>api_svc.traditional_knowledge_label</code> table
     * reference
     */
    public TraditionalKnowledgeLabel(Name alias) {
        this(alias, TRADITIONAL_KNOWLEDGE_LABEL);
    }

    /**
     * Create a <code>api_svc.traditional_knowledge_label</code> table reference
     */
    public TraditionalKnowledgeLabel() {
        this(DSL.name("traditional_knowledge_label"), null);
    }

    public <O extends Record> TraditionalKnowledgeLabel(Table<O> child, ForeignKey<O, TraditionalKnowledgeLabelRecord> key) {
        super(child, key, TRADITIONAL_KNOWLEDGE_LABEL);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : ApiSvc.API_SVC;
    }

    @Override
    public UniqueKey<TraditionalKnowledgeLabelRecord> getPrimaryKey() {
        return Keys.TRADITIONAL_KNOWLEDGE_LABEL_PKEY;
    }

    @Override
    public List<ForeignKey<TraditionalKnowledgeLabelRecord, ?>> getReferences() {
        return Arrays.asList(Keys.TRADITIONAL_KNOWLEDGE_LABEL__FK_TRADITIONAL_KNOWLEDGE_LABEL_ID);
    }

    private transient TraditionalKnowledgeLabelSchema _traditionalKnowledgeLabelSchema;

    /**
     * Get the implicit join path to the
     * <code>api_svc.traditional_knowledge_label_schema</code> table.
     */
    public TraditionalKnowledgeLabelSchema traditionalKnowledgeLabelSchema() {
        if (_traditionalKnowledgeLabelSchema == null)
            _traditionalKnowledgeLabelSchema = new TraditionalKnowledgeLabelSchema(this, Keys.TRADITIONAL_KNOWLEDGE_LABEL__FK_TRADITIONAL_KNOWLEDGE_LABEL_ID);

        return _traditionalKnowledgeLabelSchema;
    }

    @Override
    public TraditionalKnowledgeLabel as(String alias) {
        return new TraditionalKnowledgeLabel(DSL.name(alias), this);
    }

    @Override
    public TraditionalKnowledgeLabel as(Name alias) {
        return new TraditionalKnowledgeLabel(alias, this);
    }

    @Override
    public TraditionalKnowledgeLabel as(Table<?> alias) {
        return new TraditionalKnowledgeLabel(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public TraditionalKnowledgeLabel rename(String name) {
        return new TraditionalKnowledgeLabel(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public TraditionalKnowledgeLabel rename(Name name) {
        return new TraditionalKnowledgeLabel(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public TraditionalKnowledgeLabel rename(Table<?> name) {
        return new TraditionalKnowledgeLabel(name.getQualifiedName(), null);
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
