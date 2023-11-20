package au.org.raid.api.repository;

import au.org.raid.db.jooq.tables.records.AccessTypeRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static au.org.raid.db.jooq.tables.AccessType.ACCESS_TYPE;

@Repository
@RequiredArgsConstructor
public class AccessTypeRepository {
    private final DSLContext dslContext;

    public Optional<AccessTypeRecord> findByUriAndSchemaId(final String uri, final int schemaId) {
        return dslContext.select(ACCESS_TYPE.fields())
                .from(ACCESS_TYPE)
                .where(ACCESS_TYPE.URI.eq(uri)
                        .and(ACCESS_TYPE.SCHEMA_ID.eq(schemaId)))
                .fetchOptional(record -> new AccessTypeRecord()
                        .setSchemaId(ACCESS_TYPE.SCHEMA_ID.getValue(record))
                        .setUri(ACCESS_TYPE.URI.getValue(record))
                );
    }
}