/*
 * This file is generated by jOOQ.
 */
package au.org.raid.db.jooq.tables.records;


import au.org.raid.db.jooq.tables.ContributorSchema;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ContributorSchemaRecord extends UpdatableRecordImpl<ContributorSchemaRecord> implements Record2<Integer, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>api_svc.contributor_schema.id</code>.
     */
    public ContributorSchemaRecord setId(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>api_svc.contributor_schema.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>api_svc.contributor_schema.uri</code>.
     */
    public ContributorSchemaRecord setUri(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>api_svc.contributor_schema.uri</code>.
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
        return ContributorSchema.CONTRIBUTOR_SCHEMA.ID;
    }

    @Override
    public Field<String> field2() {
        return ContributorSchema.CONTRIBUTOR_SCHEMA.URI;
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
    public ContributorSchemaRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public ContributorSchemaRecord value2(String value) {
        setUri(value);
        return this;
    }

    @Override
    public ContributorSchemaRecord values(Integer value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ContributorSchemaRecord
     */
    public ContributorSchemaRecord() {
        super(ContributorSchema.CONTRIBUTOR_SCHEMA);
    }

    /**
     * Create a detached, initialised ContributorSchemaRecord
     */
    public ContributorSchemaRecord(Integer id, String uri) {
        super(ContributorSchema.CONTRIBUTOR_SCHEMA);

        setId(id);
        setUri(uri);
    }
}
