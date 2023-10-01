package au.org.raid.api.validator;

import au.org.raid.idl.raidv2.model.Description;
import au.org.raid.idl.raidv2.model.ValidationFailure;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static au.org.raid.api.endpoint.message.ValidationMessage.NOT_SET_MESSAGE;
import static au.org.raid.api.endpoint.message.ValidationMessage.NOT_SET_TYPE;
import static au.org.raid.api.util.StringUtil.isBlank;

@Component
@RequiredArgsConstructor
public class DescriptionValidator {

    private final DescriptionTypeValidator typeValidationService;
    private final LanguageValidator languageValidator;

    public List<ValidationFailure> validate(
            List<Description> descriptions
    ) {
        if (descriptions == null) {
            // allowed to have no desc, not sure if parser will pass through null
            // or empty if property is not set at all - either way, it's allowed
            return Collections.emptyList();
        }

        var failures = new ArrayList<ValidationFailure>();

        IntStream.range(0, descriptions.size()).forEach(index -> {
            final var description = descriptions.get(index);

            if (isBlank(description.getText())) {
                failures.add(new ValidationFailure()
                        .fieldId("description[%d].text".formatted(index))
                        .errorType(NOT_SET_TYPE)
                        .message(NOT_SET_MESSAGE)
                );
            }

            failures.addAll(typeValidationService.validate(description.getType(), index));
            failures.addAll(
                    languageValidator.validate(description.getLanguage(), "description[%d]".formatted(index))
            );
        });

        return failures;
    }
}