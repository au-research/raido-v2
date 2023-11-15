/*
 * This file is generated by jOOQ.
 */
package au.org.raid.db.jooq.api_svc.enums;


import au.org.raid.db.jooq.api_svc.ApiSvc;

import org.jooq.Catalog;
import org.jooq.EnumType;
import org.jooq.Schema;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public enum Metaschema implements EnumType {

    raido_metadata_schema_v1("raido-metadata-schema-v1"),

    legacy_metadata_schema_v1("legacy-metadata-schema-v1"),

    raido_metadata_schema_v2("raido-metadata-schema-v2");

    private final String literal;

    private Metaschema(String literal) {
        this.literal = literal;
    }

    @Override
    public Catalog getCatalog() {
        return getSchema().getCatalog();
    }

    @Override
    public Schema getSchema() {
        return ApiSvc.API_SVC;
    }

    @Override
    public String getName() {
        return "metaschema";
    }

    @Override
    public String getLiteral() {
        return literal;
    }

    /**
     * Lookup a value of this EnumType by its literal
     */
    public static Metaschema lookupLiteral(String literal) {
        return EnumType.lookupLiteral(Metaschema.class, literal);
    }
}