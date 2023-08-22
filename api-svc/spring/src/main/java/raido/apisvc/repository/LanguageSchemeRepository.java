package raido.apisvc.repository;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import raido.db.jooq.api_svc.tables.records.LanguageSchemeRecord;

import java.util.Optional;

import static raido.db.jooq.api_svc.tables.LanguageScheme.LANGUAGE_SCHEME;

@Repository
@RequiredArgsConstructor
public class LanguageSchemeRepository {
    private final DSLContext dslContext;
    
    public Optional<LanguageSchemeRecord> findByUri(final String uri) {
        return dslContext.select(LANGUAGE_SCHEME.fields()).
                from(LANGUAGE_SCHEME).
                where(LANGUAGE_SCHEME.URI.eq(uri)).
                fetchOptional(record -> new LanguageSchemeRecord()
                        .setId(LANGUAGE_SCHEME.ID.getValue(record))
                        .setUri(LANGUAGE_SCHEME.URI.getValue(record))
                );
    }

}
