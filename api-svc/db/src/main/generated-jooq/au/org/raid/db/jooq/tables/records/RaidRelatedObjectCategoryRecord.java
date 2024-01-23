/*
 * This file is generated by jOOQ.
 */
package au.org.raid.db.jooq.tables.records;


import au.org.raid.db.jooq.tables.RaidRelatedObjectCategory;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RaidRelatedObjectCategoryRecord extends UpdatableRecordImpl<RaidRelatedObjectCategoryRecord> implements Record3<Integer, Integer, Integer> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>api_svc.raid_related_object_category.id</code>.
     */
    public RaidRelatedObjectCategoryRecord setId(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>api_svc.raid_related_object_category.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for
     * <code>api_svc.raid_related_object_category.raid_related_object_id</code>.
     */
    public RaidRelatedObjectCategoryRecord setRaidRelatedObjectId(Integer value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for
     * <code>api_svc.raid_related_object_category.raid_related_object_id</code>.
     */
    public Integer getRaidRelatedObjectId() {
        return (Integer) get(1);
    }

    /**
     * Setter for
     * <code>api_svc.raid_related_object_category.related_object_category_id</code>.
     */
    public RaidRelatedObjectCategoryRecord setRelatedObjectCategoryId(Integer value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for
     * <code>api_svc.raid_related_object_category.related_object_category_id</code>.
     */
    public Integer getRelatedObjectCategoryId() {
        return (Integer) get(2);
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
    public Row3<Integer, Integer, Integer> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<Integer, Integer, Integer> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return RaidRelatedObjectCategory.RAID_RELATED_OBJECT_CATEGORY.ID;
    }

    @Override
    public Field<Integer> field2() {
        return RaidRelatedObjectCategory.RAID_RELATED_OBJECT_CATEGORY.RAID_RELATED_OBJECT_ID;
    }

    @Override
    public Field<Integer> field3() {
        return RaidRelatedObjectCategory.RAID_RELATED_OBJECT_CATEGORY.RELATED_OBJECT_CATEGORY_ID;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public Integer component2() {
        return getRaidRelatedObjectId();
    }

    @Override
    public Integer component3() {
        return getRelatedObjectCategoryId();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public Integer value2() {
        return getRaidRelatedObjectId();
    }

    @Override
    public Integer value3() {
        return getRelatedObjectCategoryId();
    }

    @Override
    public RaidRelatedObjectCategoryRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public RaidRelatedObjectCategoryRecord value2(Integer value) {
        setRaidRelatedObjectId(value);
        return this;
    }

    @Override
    public RaidRelatedObjectCategoryRecord value3(Integer value) {
        setRelatedObjectCategoryId(value);
        return this;
    }

    @Override
    public RaidRelatedObjectCategoryRecord values(Integer value1, Integer value2, Integer value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached RaidRelatedObjectCategoryRecord
     */
    public RaidRelatedObjectCategoryRecord() {
        super(RaidRelatedObjectCategory.RAID_RELATED_OBJECT_CATEGORY);
    }

    /**
     * Create a detached, initialised RaidRelatedObjectCategoryRecord
     */
    public RaidRelatedObjectCategoryRecord(Integer id, Integer raidRelatedObjectId, Integer relatedObjectCategoryId) {
        super(RaidRelatedObjectCategory.RAID_RELATED_OBJECT_CATEGORY);

        setId(id);
        setRaidRelatedObjectId(raidRelatedObjectId);
        setRelatedObjectCategoryId(relatedObjectCategoryId);
    }
}