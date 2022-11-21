package com.note.gestion.model.validator;

import com.note.gestion.exception.BadRequestException;
import com.note.gestion.model.Semestre;
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
public class SemestreValidator implements Consumer<Semestre> {
    private final Validator validator;

    public void accept(List<Semestre> semestres) {
        semestres.forEach(this);
    }

    @Override public void accept(Semestre semestre) {
        Set<ConstraintViolation<Semestre>> violations = validator.validate(semestre);
        if (!violations.isEmpty()) {
            String constraintMessages = violations
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(". "));
            throw new BadRequestException(constraintMessages);
        }
    }
}
