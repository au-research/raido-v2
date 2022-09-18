/*
 * This file is generated by jOOQ.
 */
package raido.db.jooq.api_svc.tables;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function9;
import org.jooq.JSONB;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row9;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import raido.db.jooq.api_svc.ApiSvc;
import raido.db.jooq.api_svc.Keys;
import raido.db.jooq.api_svc.tables.records.RaidRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Raid extends TableImpl<RaidRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>api_svc.raid</code>
     */
    public static final Raid RAID = new Raid();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<RaidRecord> getRecordType() {
        return RaidRecord.class;
    }

    /**
     * The column <code>api_svc.raid.handle</code>.
     */
    public final TableField<RaidRecord, String> HANDLE = createField(DSL.name("handle"), SQLDataType.VARCHAR(32).nullable(false), this, "");

    /**
     * The column <code>api_svc.raid.service_point_id</code>.
     */
    public final TableField<RaidRecord, Long> SERVICE_POINT_ID = createField(DSL.name("service_point_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>api_svc.raid.content_path</code>.
     */
    public final TableField<RaidRecord, String> CONTENT_PATH = createField(DSL.name("content_path"), SQLDataType.VARCHAR(512).nullable(false), this, "");

    /**
     * The column <code>api_svc.raid.content_index</code>.
     */
    public final TableField<RaidRecord, Integer> CONTENT_INDEX = createField(DSL.name("content_index"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>api_svc.raid.name</code>.
     */
    public final TableField<RaidRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(256).nullable(false), this, "");

    /**
     * The column <code>api_svc.raid.description</code>.
     */
    public final TableField<RaidRecord, String> DESCRIPTION = createField(DSL.name("description"), SQLDataType.VARCHAR(1024).nullable(false), this, "");

    /**
     * The column <code>api_svc.raid.dmr</code>.
     */
    public final TableField<RaidRecord, JSONB> DMR = createField(DSL.name("dmr"), SQLDataType.JSONB.nullable(false), this, "");

    /**
     * The column <code>api_svc.raid.start_date</code>.
     */
    public final TableField<RaidRecord, LocalDateTime> START_DATE = createField(DSL.name("start_date"), SQLDataType.LOCALDATETIME(6).nullable(false).defaultValue(DSL.field("transaction_timestamp()", SQLDataType.LOCALDATETIME)), this, "");

    /**
     * The column <code>api_svc.raid.date_created</code>.
     */
    public final TableField<RaidRecord, LocalDateTime> DATE_CREATED = createField(DSL.name("date_created"), SQLDataType.LOCALDATETIME(6).nullable(false).defaultValue(DSL.field("transaction_timestamp()", SQLDataType.LOCALDATETIME)), this, "");

    private Raid(Name alias, Table<RaidRecord> aliased) {
        this(alias, aliased, null);
    }

    private Raid(Name alias, Table<RaidRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>api_svc.raid</code> table reference
     */
    public Raid(String alias) {
        this(DSL.name(alias), RAID);
    }

    /**
     * Create an aliased <code>api_svc.raid</code> table reference
     */
    public Raid(Name alias) {
        this(alias, RAID);
    }

    /**
     * Create a <code>api_svc.raid</code> table reference
     */
    public Raid() {
        this(DSL.name("raid"), null);
    }

    public <O extends Record> Raid(Table<O> child, ForeignKey<O, RaidRecord> key) {
        super(child, key, RAID);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : ApiSvc.API_SVC;
    }

    @Override
    public UniqueKey<RaidRecord> getPrimaryKey() {
        return Keys.RAID_PKEY;
    }

    @Override
    public List<ForeignKey<RaidRecord, ?>> getReferences() {
        return Arrays.asList(Keys.RAID__RAID_SERVICE_POINT_ID_FKEY);
    }

    private transient ServicePoint _servicePoint;

    /**
     * Get the implicit join path to the <code>api_svc.service_point</code>
     * table.
     */
    public ServicePoint servicePoint() {
        if (_servicePoint == null)
            _servicePoint = new ServicePoint(this, Keys.RAID__RAID_SERVICE_POINT_ID_FKEY);

        return _servicePoint;
    }

    @Override
    public Raid as(String alias) {
        return new Raid(DSL.name(alias), this);
    }

    @Override
    public Raid as(Name alias) {
        return new Raid(alias, this);
    }

    @Override
    public Raid as(Table<?> alias) {
        return new Raid(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Raid rename(String name) {
        return new Raid(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Raid rename(Name name) {
        return new Raid(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Raid rename(Table<?> name) {
        return new Raid(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row9 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row9<String, Long, String, Integer, String, String, JSONB, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row9) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function9<? super String, ? super Long, ? super String, ? super Integer, ? super String, ? super String, ? super JSONB, ? super LocalDateTime, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function9<? super String, ? super Long, ? super String, ? super Integer, ? super String, ? super String, ? super JSONB, ? super LocalDateTime, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}