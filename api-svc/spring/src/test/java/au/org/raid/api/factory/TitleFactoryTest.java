package au.org.raid.api.factory;

import au.org.raid.idl.raidv2.model.Title;
import au.org.raid.idl.raidv2.model.TitleBlock;
import au.org.raid.idl.raidv2.model.TitleType;
import au.org.raid.idl.raidv2.model.TitleTypeWithSchemaUri;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

class TitleFactoryTest {
    private static final String PRIMARY_ID =
            "https://github.com/au-research/raid-metadata/blob/main/scheme/title/type/v1/primary.json";
    private static final String ALTERNATIVE_ID =
            "https://github.com/au-research/raid-metadata/blob/main/scheme/title/type/v1/alternative.json";
    private static final String TITLE_TYPE_SCHEMA_URI =
            "https://github.com/au-research/raid-metadata/tree/main/scheme/title/type/v1/";

    private static final LocalDate START_DATE = LocalDate.now().minusYears(2);
    private static final LocalDate END_DATE = LocalDate.now().minusYears(1);
    private static final String TITLE = "Test Title";

    private final TitleFactory titleFactory = new TitleFactory();

    @Test
    @DisplayName("Sets primary title type")
    void setsPrimaryType() {
        final var title = new TitleBlock()
                .startDate(START_DATE)
                .endDate(END_DATE)
                .title(TITLE)
                .type(TitleType.PRIMARY_TITLE);

        final var result = titleFactory.create(title);

        final var expected = new Title()
                .text(TITLE)
                .startDate(START_DATE.format(DateTimeFormatter.ISO_LOCAL_DATE))
                .endDate(END_DATE.format(DateTimeFormatter.ISO_LOCAL_DATE))
                .type(new TitleTypeWithSchemaUri()
                        .id(PRIMARY_ID)
                        .schemaUri(TITLE_TYPE_SCHEMA_URI));

        assertThat(result, is(expected));
    }

    @Test
    @DisplayName("Sets alternative title type")
    void setsAlternativeType() {
        final var title = new TitleBlock()
                .startDate(START_DATE)
                .endDate(END_DATE)
                .title(TITLE)
                .type(TitleType.ALTERNATIVE_TITLE);

        final var result = titleFactory.create(title);

        final var expected = new Title()
                .text(TITLE)
                .startDate(START_DATE.format(DateTimeFormatter.ISO_LOCAL_DATE))
                .endDate(END_DATE.format(DateTimeFormatter.ISO_LOCAL_DATE))
                .type(new TitleTypeWithSchemaUri()
                        .id(ALTERNATIVE_ID)
                        .schemaUri(TITLE_TYPE_SCHEMA_URI));

        assertThat(result, is(expected));
    }

    @Test
    @DisplayName("Null TitleBlock returns null")
    void returnsNull() {
        assertThat(titleFactory.create(null), nullValue());
    }

    @Test
    @DisplayName("Doesn't throw NullPointerException")
    void noNpe() {
        final var title = new TitleBlock();

        final var result = titleFactory.create(title);

        final var expected = new Title()
                .type(new TitleTypeWithSchemaUri()
                        .schemaUri(TITLE_TYPE_SCHEMA_URI));

        assertThat(result, is(expected));
    }
}