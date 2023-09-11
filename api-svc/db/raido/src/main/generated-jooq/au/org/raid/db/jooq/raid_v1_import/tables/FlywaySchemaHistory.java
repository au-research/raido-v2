/*
 * This file is generated by jOOQ.
 */
package au.org.raid.db.jooq.raid_v1_import.tables;


import au.org.raid.db.jooq.raid_v1_import.Indexes;
import au.org.raid.db.jooq.raid_v1_import.Keys;
import au.org.raid.db.jooq.raid_v1_import.RaidV1Import;
import au.org.raid.db.jooq.raid_v1_import.tables.records.FlywaySchemaHistoryRecord;
import org.jooq.Record;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class FlywaySchemaHistory extends TableImpl<FlywaySchemaHistoryRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of
     * <code>raid_v1_import.flyway_schema_history</code>
     */
    public static final FlywaySchemaHistory FLYWAY_SCHEMA_HISTORY = new FlywaySchemaHistory();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<FlywaySchemaHistoryRecord> getRecordType() {
        return FlywaySchemaHistoryRecord.class;
    }

    /**
     * The column
     * <code>raid_v1_import.flyway_schema_history.installed_rank</code>.
     */
    public final TableField<FlywaySchemaHistoryRecord, Integer> INSTALLED_RANK = createField(DSL.name("installed_rank"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>raid_v1_import.flyway_schema_history.version</code>.
     */
    public final TableField<FlywaySchemaHistoryRecord, String> VERSION = createField(DSL.name("version"), SQLDataType.VARCHAR(50), this, "");

    /**
     * The column <code>raid_v1_import.flyway_schema_history.description</code>.
     */
    public final TableField<FlywaySchemaHistoryRecord, String> DESCRIPTION = createField(DSL.name("description"), SQLDataType.VARCHAR(200).nullable(false), this, "");

    /**
     * The column <code>raid_v1_import.flyway_schema_history.type</code>.
     */
    public final TableField<FlywaySchemaHistoryRecord, String> TYPE = createField(DSL.name("type"), SQLDataType.VARCHAR(20).nullable(false), this, "");

    /**
     * The column <code>raid_v1_import.flyway_schema_history.script</code>.
     */
    public final TableField<FlywaySchemaHistoryRecord, String> SCRIPT = createField(DSL.name("script"), SQLDataType.VARCHAR(1000).nullable(false), this, "");

    /**
     * The column <code>raid_v1_import.flyway_schema_history.checksum</code>.
     */
    public final TableField<FlywaySchemaHistoryRecord, Integer> CHECKSUM = createField(DSL.name("checksum"), SQLDataType.INTEGER, this, "");

    /**
     * The column
     * <code>raid_v1_import.flyway_schema_history.installed_by</code>.
     */
    public final TableField<FlywaySchemaHistoryRecord, String> INSTALLED_BY = createField(DSL.name("installed_by"), SQLDataType.VARCHAR(100).nullable(false), this, "");

    /**
     * The column
     * <code>raid_v1_import.flyway_schema_history.installed_on</code>.
     */
    public final TableField<FlywaySchemaHistoryRecord, LocalDateTime> INSTALLED_ON = createField(DSL.name("installed_on"), SQLDataType.LOCALDATETIME(6).nullable(false).defaultValue(DSL.field(DSL.raw("now()"), SQLDataType.LOCALDATETIME)), this, "");

    /**
     * The column
     * <code>raid_v1_import.flyway_schema_history.execution_time</code>.
     */
    public final TableField<FlywaySchemaHistoryRecord, Integer> EXECUTION_TIME = createField(DSL.name("execution_time"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>raid_v1_import.flyway_schema_history.success</code>.
     */
    public final TableField<FlywaySchemaHistoryRecord, Boolean> SUCCESS = createField(DSL.name("success"), SQLDataType.BOOLEAN.nullable(false), this, "");

    private FlywaySchemaHistory(Name alias, Table<FlywaySchemaHistoryRecord> aliased) {
        this(alias, aliased, null);
    }

    private FlywaySchemaHistory(Name alias, Table<FlywaySchemaHistoryRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>raid_v1_import.flyway_schema_history</code> table
     * reference
     */
    public FlywaySchemaHistory(String alias) {
        this(DSL.name(alias), FLYWAY_SCHEMA_HISTORY);
    }

    /**
     * Create an aliased <code>raid_v1_import.flyway_schema_history</code> table
     * reference
     */
    public FlywaySchemaHistory(Name alias) {
        this(alias, FLYWAY_SCHEMA_HISTORY);
    }

    /**
     * Create a <code>raid_v1_import.flyway_schema_history</code> table
     * reference
     */
    public FlywaySchemaHistory() {
        this(DSL.name("flyway_schema_history"), null);
    }

    public <O extends Record> FlywaySchemaHistory(Table<O> child, ForeignKey<O, FlywaySchemaHistoryRecord> key) {
        super(child, key, FLYWAY_SCHEMA_HISTORY);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : RaidV1Import.RAID_V1_IMPORT;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.FLYWAY_SCHEMA_HISTORY_S_IDX);
    }

    @Override
    public UniqueKey<FlywaySchemaHistoryRecord> getPrimaryKey() {
        return Keys.FLYWAY_SCHEMA_HISTORY_PK;
    }

    @Override
    public FlywaySchemaHistory as(String alias) {
        return new FlywaySchemaHistory(DSL.name(alias), this);
    }

    @Override
    public FlywaySchemaHistory as(Name alias) {
        return new FlywaySchemaHistory(alias, this);
    }

    @Override
    public FlywaySchemaHistory as(Table<?> alias) {
        return new FlywaySchemaHistory(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public FlywaySchemaHistory rename(String name) {
        return new FlywaySchemaHistory(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public FlywaySchemaHistory rename(Name name) {
        return new FlywaySchemaHistory(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public FlywaySchemaHistory rename(Table<?> name) {
        return new FlywaySchemaHistory(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row10 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row10<Integer, String, String, String, String, Integer, String, LocalDateTime, Integer, Boolean> fieldsRow() {
        return (Row10) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function10<? super Integer, ? super String, ? super String, ? super String, ? super String, ? super Integer, ? super String, ? super LocalDateTime, ? super Integer, ? super Boolean, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function10<? super Integer, ? super String, ? super String, ? super String, ? super String, ? super Integer, ? super String, ? super LocalDateTime, ? super Integer, ? super Boolean, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}