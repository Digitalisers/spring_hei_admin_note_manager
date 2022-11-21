package com.note.gestion.model.validator;

import com.note.gestion.exception.BadRequestException;
import com.note.gestion.model.Mention;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class MentionValidator implements Consumer<Mention> {
    private final Validator validator;

    public void accept(List<Mention> mentions) {
        mentions.forEach(this);
    }

    @Override public void accept(Mention mention) {
        Set<ConstraintViolation<Mention>> violations = validator.validate(mention);
        if (!violations.isEmpty()) {
            String constraintMessages = violations
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(". "));
            throw new BadRequestException(constraintMessages);
        }
    }
}
