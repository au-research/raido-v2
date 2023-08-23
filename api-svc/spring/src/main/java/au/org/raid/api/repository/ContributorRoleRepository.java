package au.org.raid.api.repository;

import au.org.raid.db.jooq.api_svc.tables.records.ContributorRoleRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static au.org.raid.db.jooq.api_svc.tables.ContributorRole.CONTRIBUTOR_ROLE;

@Repository
@RequiredArgsConstructor
public class ContributorRoleRepository {
    private final DSLContext dslContext;

    public Optional<ContributorRoleRecord> findByUriAndSchemeId(final String uri, final int schemeId) {
        return dslContext.select(CONTRIBUTOR_ROLE.fields())
                .from(CONTRIBUTOR_ROLE)
                .where(CONTRIBUTOR_ROLE.URI.eq(uri)
                        .and(CONTRIBUTOR_ROLE.SCHEME_ID.eq(schemeId)))
                .fetchOptional(record -> new ContributorRoleRecord()
                        .setSchemeId(CONTRIBUTOR_ROLE.SCHEME_ID.getValue(record))
                        .setUri(CONTRIBUTOR_ROLE.URI.getValue(record))
                );
    }
}