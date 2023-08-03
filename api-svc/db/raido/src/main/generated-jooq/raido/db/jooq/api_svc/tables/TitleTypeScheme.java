/*
 * This file is generated by jOOQ.
 */
package raido.db.jooq.api_svc.tables;


import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function2;
import org.jooq.Identity;
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

import raido.db.jooq.api_svc.ApiSvc;
import raido.db.jooq.api_svc.Keys;
import raido.db.jooq.api_svc.tables.records.TitleTypeSchemeRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TitleTypeScheme extends TableImpl<TitleTypeSchemeRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>api_svc.title_type_scheme</code>
     */
    public static final TitleTypeScheme TITLE_TYPE_SCHEME = new TitleTypeScheme();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TitleTypeSchemeRecord> getRecordType() {
        return TitleTypeSchemeRecord.class;
    }

    /**
     * The column <code>api_svc.title_type_scheme.id</code>.
     */
    public final TableField<TitleTypeSchemeRecord, Integer> ID = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>api_svc.title_type_scheme.uri</code>.
     */
    public final TableField<TitleTypeSchemeRecord, String> URI = createField(DSL.name("uri"), SQLDataType.VARCHAR.nullable(false), this, "");

    private TitleTypeScheme(Name alias, Table<TitleTypeSchemeRecord> aliased) {
        this(alias, aliased, null);
    }

    private TitleTypeScheme(Name alias, Table<TitleTypeSchemeRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>api_svc.title_type_scheme</code> table reference
     */
    public TitleTypeScheme(String alias) {
        this(DSL.name(alias), TITLE_TYPE_SCHEME);
    }

    /**
     * Create an aliased <code>api_svc.title_type_scheme</code> table reference
     */
    public TitleTypeScheme(Name alias) {
        this(alias, TITLE_TYPE_SCHEME);
    }

    /**
     * Create a <code>api_svc.title_type_scheme</code> table reference
     */
    public TitleTypeScheme() {
        this(DSL.name("title_type_scheme"), null);
    }

    public <O extends Record> TitleTypeScheme(Table<O> child, ForeignKey<O, TitleTypeSchemeRecord> key) {
        super(child, key, TITLE_TYPE_SCHEME);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : ApiSvc.API_SVC;
    }

    @Override
    public Identity<TitleTypeSchemeRecord, Integer> getIdentity() {
        return (Identity<TitleTypeSchemeRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<TitleTypeSchemeRecord> getPrimaryKey() {
        return Keys.TITLE_TYPE_SCHEME_PKEY;
    }

    @Override
    public TitleTypeScheme as(String alias) {
        return new TitleTypeScheme(DSL.name(alias), this);
    }

    @Override
    public TitleTypeScheme as(Name alias) {
        return new TitleTypeScheme(alias, this);
    }

    @Override
    public TitleTypeScheme as(Table<?> alias) {
        return new TitleTypeScheme(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public TitleTypeScheme rename(String name) {
        return new TitleTypeScheme(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public TitleTypeScheme rename(Name name) {
        return new TitleTypeScheme(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public TitleTypeScheme rename(Table<?> name) {
        return new TitleTypeScheme(name.getQualifiedName(), null);
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
