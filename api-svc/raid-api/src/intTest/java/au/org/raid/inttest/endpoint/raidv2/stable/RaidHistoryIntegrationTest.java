package au.org.raid.inttest.endpoint.raidv2.stable;

import au.org.raid.api.service.Handle;
import au.org.raid.db.jooq.enums.UserRole;
import au.org.raid.idl.raidv2.model.RaidDto;
import au.org.raid.idl.raidv2.model.Title;
import au.org.raid.idl.raidv2.model.TitleTypeWithSchemaUri;
import feign.FeignException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Objects;
import java.util.stream.IntStream;

import static au.org.raid.inttest.endpoint.raidv2.stable.TestConstants.PRIMARY_TITLE_TYPE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class RaidHistoryIntegrationTest extends AbstractIntegrationTest {
    @Test
    @DisplayName("Changes are saved to history table")
    void changesSaved() {

        createRequest.getTitle().get(0).setText("Version 1");

        final var createResponse = raidApi.createRaidV1(createRequest);

        Handle handle = new Handle(Objects.requireNonNull(createResponse.getBody()).getIdentifier().getId());

        var raid = createResponse.getBody();

        IntStream.range(1,7).forEach(i -> {
            final var text = "Version %d".formatted(i + 1);

            final var primaryTitle = getPrimaryTitle(raid);

            primaryTitle.setText(text);
            raid.getIdentifier().setVersion(i);

            raidApi.updateRaidV1(handle.getPrefix(), handle.getSuffix(), raidUpdateRequestFactory.create(raid));

            final var response = raidApi.readRaidV1(handle.getPrefix(), handle.getSuffix(), null);

            final var raidDto = response.getBody();
            assert raidDto != null;

            assertThat(raidDto.getIdentifier().getVersion()).isEqualTo(i + 1);
            assertThat(getPrimaryTitle(raidDto).getText()).isEqualTo(text);
        });

        final var version = 3;

        final var response = raidApi.readRaidV1(handle.getPrefix(), handle.getSuffix(), version);
        final var raidDto = response.getBody();
        assert raidDto != null;

        assertThat(raidDto.getIdentifier().getVersion()).isEqualTo(version);
        assertThat(raidDto.getTitle().get(0).getText()).isEqualTo("Version %d".formatted(version));

        final var historyResponse = raidApi.raidHistory(handle.getPrefix(), handle.getSuffix());

        final var history = historyResponse.getBody();

        assertThat(history).hasSize(7);
    }

    @Test
    @DisplayName("History of embargoed raid is not accessible by user from different service point")
    void embargoedRaid() {
        final var createResponse = raidApi.createRaidV1(createRequest);

        Handle handle = new Handle(Objects.requireNonNull(createResponse.getBody()).getIdentifier().getId());

        final var token = bootstrapTokenSvc.bootstrapToken(UQ_SERVICE_POINT_ID, "int-test-uq-user", UserRole.SP_USER);

        final var otherClient = testClient.raidApi(token);

        try {
            otherClient.raidHistory(handle.getPrefix(), handle.getSuffix());
            fail("Should return 403 status");
        } catch (Exception e) {
            assertThat(e).isInstanceOf(FeignException.Forbidden.class);
        }
    }

    private Title getPrimaryTitle(final RaidDto raidDto) {
        return raidDto.getTitle().stream()
                .filter(title -> title.getType().getId().equals(PRIMARY_TITLE_TYPE))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No primary title :("));
    }

}