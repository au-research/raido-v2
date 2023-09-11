package au.org.raid.api.repository;

import au.org.raid.db.jooq.api_svc.tables.records.RelatedObjectCategoryRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static au.org.raid.db.jooq.api_svc.tables.RelatedObjectCategory.RELATED_OBJECT_CATEGORY;

@Repository
@RequiredArgsConstructor
public class RelatedObjectCategoryRepository {
    private final DSLContext dslContext;

    public Optional<RelatedObjectCategoryRecord> findByUriAndSchemeId(final String uri, final int schemeId) {
        return dslContext.select(RELATED_OBJECT_CATEGORY.fields())
                .from(RELATED_OBJECT_CATEGORY)
                .where(RELATED_OBJECT_CATEGORY.URI.eq(uri)
                        .and(RELATED_OBJECT_CATEGORY.SCHEME_ID.eq(schemeId)))
                .fetchOptional(record -> new RelatedObjectCategoryRecord()
                        .setSchemeId(RELATED_OBJECT_CATEGORY.SCHEME_ID.getValue(record))
                        .setUri(RELATED_OBJECT_CATEGORY.URI.getValue(record))
                );
    }
}