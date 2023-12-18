/*
 * This file is generated by jOOQ.
 */
package au.org.raid.db.jooq.tables.records;


import au.org.raid.db.jooq.tables.RaidAlternateIdentifier;

import org.jooq.Field;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RaidAlternateIdentifierRecord extends UpdatableRecordImpl<RaidAlternateIdentifierRecord> implements Record3<String, String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>api_svc.raid_alternate_identifier.handle</code>.
     */
    public RaidAlternateIdentifierRecord setHandle(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>api_svc.raid_alternate_identifier.handle</code>.
     */
    public String getHandle() {
        return (String) get(0);
    }

    /**
     * Setter for <code>api_svc.raid_alternate_identifier.id</code>.
     */
    public RaidAlternateIdentifierRecord setId(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>api_svc.raid_alternate_identifier.id</code>.
     */
    public String getId() {
        return (String) get(1);
    }

    /**
     * Setter for <code>api_svc.raid_alternate_identifier.type</code>.
     */
    public RaidAlternateIdentifierRecord setType(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>api_svc.raid_alternate_identifier.type</code>.
     */
    public String getType() {
        return (String) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record3<String, String, String> key() {
        return (Record3) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<String, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<String, String, String> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return RaidAlternateIdentifier.RAID_ALTERNATE_IDENTIFIER.HANDLE;
    }

    @Override
    public Field<String> field2() {
        return RaidAlternateIdentifier.RAID_ALTERNATE_IDENTIFIER.ID;
    }

    @Override
    public Field<String> field3() {
        return RaidAlternateIdentifier.RAID_ALTERNATE_IDENTIFIER.TYPE;
    }

    @Override
    public String component1() {
        return getHandle();
    }

    @Override
    public String component2() {
        return getId();
    }

    @Override
    public String component3() {
        return getType();
    }

    @Override
    public String value1() {
        return getHandle();
    }

    @Override
    public String value2() {
        return getId();
    }

    @Override
    public String value3() {
        return getType();
    }

    @Override
    public RaidAlternateIdentifierRecord value1(String value) {
        setHandle(value);
        return this;
    }

    @Override
    public RaidAlternateIdentifierRecord value2(String value) {
        setId(value);
        return this;
    }

    @Override
    public RaidAlternateIdentifierRecord value3(String value) {
        setType(value);
        return this;
    }

    @Override
    public RaidAlternateIdentifierRecord values(String value1, String value2, String value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached RaidAlternateIdentifierRecord
     */
    public RaidAlternateIdentifierRecord() {
        super(RaidAlternateIdentifier.RAID_ALTERNATE_IDENTIFIER);
    }

    /**
     * Create a detached, initialised RaidAlternateIdentifierRecord
     */
    public RaidAlternateIdentifierRecord(String handle, String id, String type) {
        super(RaidAlternateIdentifier.RAID_ALTERNATE_IDENTIFIER);

        setHandle(handle);
        setId(id);
        setType(type);
    }
}
