/*
 * This file is generated by jOOQ.
 */
package au.org.raid.db.jooq.tables.records;


import au.org.raid.db.jooq.tables.RaidRelatedObject;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RaidRelatedObjectRecord extends UpdatableRecordImpl<RaidRelatedObjectRecord> implements Record5<Integer, String, Integer, Integer, Integer> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>api_svc.raid_related_object.id</code>.
     */
    public RaidRelatedObjectRecord setId(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>api_svc.raid_related_object.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>api_svc.raid_related_object.raid_name</code>.
     */
    public RaidRelatedObjectRecord setRaidName(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>api_svc.raid_related_object.raid_name</code>.
     */
    public String getRaidName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>api_svc.raid_related_object.related_object_id</code>.
     */
    public RaidRelatedObjectRecord setRelatedObjectId(Integer value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>api_svc.raid_related_object.related_object_id</code>.
     */
    public Integer getRelatedObjectId() {
        return (Integer) get(2);
    }

    /**
     * Setter for
     * <code>api_svc.raid_related_object.related_object_category_id</code>.
     */
    public RaidRelatedObjectRecord setRelatedObjectCategoryId(Integer value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for
     * <code>api_svc.raid_related_object.related_object_category_id</code>.
     */
    public Integer getRelatedObjectCategoryId() {
        return (Integer) get(3);
    }

    /**
     * Setter for
     * <code>api_svc.raid_related_object.related_object_type_id</code>.
     */
    public RaidRelatedObjectRecord setRelatedObjectTypeId(Integer value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for
     * <code>api_svc.raid_related_object.related_object_type_id</code>.
     */
    public Integer getRelatedObjectTypeId() {
        return (Integer) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row5<Integer, String, Integer, Integer, Integer> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    @Override
    public Row5<Integer, String, Integer, Integer, Integer> valuesRow() {
        return (Row5) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return RaidRelatedObject.RAID_RELATED_OBJECT.ID;
    }

    @Override
    public Field<String> field2() {
        return RaidRelatedObject.RAID_RELATED_OBJECT.RAID_NAME;
    }

    @Override
    public Field<Integer> field3() {
        return RaidRelatedObject.RAID_RELATED_OBJECT.RELATED_OBJECT_ID;
    }

    @Override
    public Field<Integer> field4() {
        return RaidRelatedObject.RAID_RELATED_OBJECT.RELATED_OBJECT_CATEGORY_ID;
    }

    @Override
    public Field<Integer> field5() {
        return RaidRelatedObject.RAID_RELATED_OBJECT.RELATED_OBJECT_TYPE_ID;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getRaidName();
    }

    @Override
    public Integer component3() {
        return getRelatedObjectId();
    }

    @Override
    public Integer component4() {
        return getRelatedObjectCategoryId();
    }

    @Override
    public Integer component5() {
        return getRelatedObjectTypeId();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getRaidName();
    }

    @Override
    public Integer value3() {
        return getRelatedObjectId();
    }

    @Override
    public Integer value4() {
        return getRelatedObjectCategoryId();
    }

    @Override
    public Integer value5() {
        return getRelatedObjectTypeId();
    }

    @Override
    public RaidRelatedObjectRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public RaidRelatedObjectRecord value2(String value) {
        setRaidName(value);
        return this;
    }

    @Override
    public RaidRelatedObjectRecord value3(Integer value) {
        setRelatedObjectId(value);
        return this;
    }

    @Override
    public RaidRelatedObjectRecord value4(Integer value) {
        setRelatedObjectCategoryId(value);
        return this;
    }

    @Override
    public RaidRelatedObjectRecord value5(Integer value) {
        setRelatedObjectTypeId(value);
        return this;
    }

    @Override
    public RaidRelatedObjectRecord values(Integer value1, String value2, Integer value3, Integer value4, Integer value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached RaidRelatedObjectRecord
     */
    public RaidRelatedObjectRecord() {
        super(RaidRelatedObject.RAID_RELATED_OBJECT);
    }

    /**
     * Create a detached, initialised RaidRelatedObjectRecord
     */
    public RaidRelatedObjectRecord(Integer id, String raidName, Integer relatedObjectId, Integer relatedObjectCategoryId, Integer relatedObjectTypeId) {
        super(RaidRelatedObject.RAID_RELATED_OBJECT);

        setId(id);
        setRaidName(raidName);
        setRelatedObjectId(relatedObjectId);
        setRelatedObjectCategoryId(relatedObjectCategoryId);
        setRelatedObjectTypeId(relatedObjectTypeId);
    }
}
