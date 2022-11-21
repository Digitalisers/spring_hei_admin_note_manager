package com.note.gestion.model.validator;

import com.note.gestion.exception.BadRequestException;
import com.note.gestion.model.Evaluation;
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
public class EvaluationValidator implements Consumer<Evaluation> {
    private final Validator validator;

    public void accept(List<Evaluation> evaluations) {
        evaluations.forEach(this);
    }

    @Override public void accept(Evaluation evaluations) {
        Set<ConstraintViolation<Evaluation>> violations = validator.validate(evaluations);
        if (!violations.isEmpty()) {
            String constraintMessages = violations
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(". "));
            throw new BadRequestException(constraintMessages);
        }
    }
}
