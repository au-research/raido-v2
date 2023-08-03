/*
 * This file is generated by jOOQ.
 */
package raido.db.jooq.api_svc.tables.records;


import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;

import raido.db.jooq.api_svc.tables.RelatedObjectTypeScheme;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RelatedObjectTypeSchemeRecord extends UpdatableRecordImpl<RelatedObjectTypeSchemeRecord> implements Record2<Integer, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>api_svc.related_object_type_scheme.id</code>.
     */
    public RelatedObjectTypeSchemeRecord setId(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>api_svc.related_object_type_scheme.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>api_svc.related_object_type_scheme.uri</code>.
     */
    public RelatedObjectTypeSchemeRecord setUri(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>api_svc.related_object_type_scheme.uri</code>.
     */
    public String getUri() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
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
        return RelatedObjectTypeScheme.RELATED_OBJECT_TYPE_SCHEME.ID;
    }

    @Override
    public Field<String> field2() {
        return RelatedObjectTypeScheme.RELATED_OBJECT_TYPE_SCHEME.URI;
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
    public Integer value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getUri();
    }

    @Override
    public RelatedObjectTypeSchemeRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public RelatedObjectTypeSchemeRecord value2(String value) {
        setUri(value);
        return this;
    }

    @Override
    public RelatedObjectTypeSchemeRecord values(Integer value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached RelatedObjectTypeSchemeRecord
     */
    public RelatedObjectTypeSchemeRecord() {
        super(RelatedObjectTypeScheme.RELATED_OBJECT_TYPE_SCHEME);
    }

    /**
     * Create a detached, initialised RelatedObjectTypeSchemeRecord
     */
    public RelatedObjectTypeSchemeRecord(Integer id, String uri) {
        super(RelatedObjectTypeScheme.RELATED_OBJECT_TYPE_SCHEME);

        setId(id);
        setUri(uri);
    }
}
