package au.org.raid.api.factory;

import au.org.raid.idl.raidv2.model.Description;
import au.org.raid.idl.raidv2.model.DescriptionBlock;
import au.org.raid.idl.raidv2.model.DescriptionType;
import au.org.raid.idl.raidv2.model.DescriptionTypeWithSchemaUri;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DescriptionFactory {
    private final String DESCRIPTION_TYPE_SCHEMA_URI =
            "https://github.com/au-research/raid-metadata/tree/main/scheme/description/type/v1/";

    private final Map<DescriptionType, String> DESCRIPTION_TYPE_MAP = Map.of(
            DescriptionType.PRIMARY_DESCRIPTION, "https://github.com/au-research/raid-metadata/blob/main/scheme/description/type/v1/primary.json",
            DescriptionType.ALTERNATIVE_DESCRIPTION, "https://github.com/au-research/raid-metadata/blob/main/scheme/description/type/v1/alternative.json"
    );

    public Description create(final DescriptionBlock descriptionBlock) {
        if (descriptionBlock == null) {
            return null;
        }

        return new Description()
                .text(descriptionBlock.getDescription())
                .type(new DescriptionTypeWithSchemaUri()
                        .id(descriptionBlock.getType() != null ? DESCRIPTION_TYPE_MAP.get(descriptionBlock.getType()) : null)
                        .schemaUri(DESCRIPTION_TYPE_SCHEMA_URI)
                );
    }
}