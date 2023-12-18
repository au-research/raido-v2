/*
 * This file is generated by jOOQ.
 */
package au.org.raid.db.jooq.tables.records;


import au.org.raid.db.jooq.tables.RaidTitle;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RaidTitleRecord extends UpdatableRecordImpl<RaidTitleRecord> implements Record7<Integer, String, Integer, String, Integer, String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>api_svc.raid_title.id</code>.
     */
    public RaidTitleRecord setId(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>api_svc.raid_title.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>api_svc.raid_title.handle</code>.
     */
    public RaidTitleRecord setHandle(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>api_svc.raid_title.handle</code>.
     */
    public String getHandle() {
        return (String) get(1);
    }

    /**
     * Setter for <code>api_svc.raid_title.title_type_id</code>.
     */
    public RaidTitleRecord setTitleTypeId(Integer value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>api_svc.raid_title.title_type_id</code>.
     */
    public Integer getTitleTypeId() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>api_svc.raid_title.text</code>.
     */
    public RaidTitleRecord setText(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>api_svc.raid_title.text</code>.
     */
    public String getText() {
        return (String) get(3);
    }

    /**
     * Setter for <code>api_svc.raid_title.language_id</code>.
     */
    public RaidTitleRecord setLanguageId(Integer value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>api_svc.raid_title.language_id</code>.
     */
    public Integer getLanguageId() {
        return (Integer) get(4);
    }

    /**
     * Setter for <code>api_svc.raid_title.start_date</code>.
     */
    public RaidTitleRecord setStartDate(String value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>api_svc.raid_title.start_date</code>.
     */
    public String getStartDate() {
        return (String) get(5);
    }

    /**
     * Setter for <code>api_svc.raid_title.end_date</code>.
     */
    public RaidTitleRecord setEndDate(String value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>api_svc.raid_title.end_date</code>.
     */
    public String getEndDate() {
        return (String) get(6);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row7<Integer, String, Integer, String, Integer, String, String> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    @Override
    public Row7<Integer, String, Integer, String, Integer, String, String> valuesRow() {
        return (Row7) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return RaidTitle.RAID_TITLE.ID;
    }

    @Override
    public Field<String> field2() {
        return RaidTitle.RAID_TITLE.HANDLE;
    }

    @Override
    public Field<Integer> field3() {
        return RaidTitle.RAID_TITLE.TITLE_TYPE_ID;
    }

    @Override
    public Field<String> field4() {
        return RaidTitle.RAID_TITLE.TEXT;
    }

    @Override
    public Field<Integer> field5() {
        return RaidTitle.RAID_TITLE.LANGUAGE_ID;
    }

    @Override
    public Field<String> field6() {
        return RaidTitle.RAID_TITLE.START_DATE;
    }

    @Override
    public Field<String> field7() {
        return RaidTitle.RAID_TITLE.END_DATE;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getHandle();
    }

    @Override
    public Integer component3() {
        return getTitleTypeId();
    }

    @Override
    public String component4() {
        return getText();
    }

    @Override
    public Integer component5() {
        return getLanguageId();
    }

    @Override
    public String component6() {
        return getStartDate();
    }

    @Override
    public String component7() {
        return getEndDate();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getHandle();
    }

    @Override
    public Integer value3() {
        return getTitleTypeId();
    }

    @Override
    public String value4() {
        return getText();
    }

    @Override
    public Integer value5() {
        return getLanguageId();
    }

    @Override
    public String value6() {
        return getStartDate();
    }

    @Override
    public String value7() {
        return getEndDate();
    }

    @Override
    public RaidTitleRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public RaidTitleRecord value2(String value) {
        setHandle(value);
        return this;
    }

    @Override
    public RaidTitleRecord value3(Integer value) {
        setTitleTypeId(value);
        return this;
    }

    @Override
    public RaidTitleRecord value4(String value) {
        setText(value);
        return this;
    }

    @Override
    public RaidTitleRecord value5(Integer value) {
        setLanguageId(value);
        return this;
    }

    @Override
    public RaidTitleRecord value6(String value) {
        setStartDate(value);
        return this;
    }

    @Override
    public RaidTitleRecord value7(String value) {
        setEndDate(value);
        return this;
    }

    @Override
    public RaidTitleRecord values(Integer value1, String value2, Integer value3, String value4, Integer value5, String value6, String value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached RaidTitleRecord
     */
    public RaidTitleRecord() {
        super(RaidTitle.RAID_TITLE);
    }

    /**
     * Create a detached, initialised RaidTitleRecord
     */
    public RaidTitleRecord(Integer id, String handle, Integer titleTypeId, String text, Integer languageId, String startDate, String endDate) {
        super(RaidTitle.RAID_TITLE);

        setId(id);
        setHandle(handle);
        setTitleTypeId(titleTypeId);
        setText(text);
        setLanguageId(languageId);
        setStartDate(startDate);
        setEndDate(endDate);
    }
}
