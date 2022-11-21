package com.note.gestion.model.validator;


import com.note.gestion.exception.BadRequestException;
import com.note.gestion.model.Grade;
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
public class GradeValidator  implements Consumer<Grade> {
    private final Validator validator;

    public void accept(List<Grade> grades) {
        grades.forEach(this);
    }

    @Override public void accept(Grade grade) {
        Set<ConstraintViolation<Grade>> violations = validator.validate(grade);
        if (!violations.isEmpty()) {
            String constraintMessages = violations
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(". "));
            throw new BadRequestException(constraintMessages);
        }
    }
}
