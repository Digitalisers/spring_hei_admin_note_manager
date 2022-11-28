package com.note.gestion.model.validator;

import com.note.gestion.exception.BadRequestException;
import com.note.gestion.model.GroupHei;
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
public class GroupValidator implements Consumer<GroupHei> {
    private final Validator validator;

    public void accept(List<GroupHei> groupHeis) {
        groupHeis.forEach(this);
    }

    @Override public void accept(GroupHei groupHei) {
        Set<ConstraintViolation<GroupHei>> violations = validator.validate(groupHei);
        if (!violations.isEmpty()) {
            String constraintMessages = violations
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(". "));
            throw new BadRequestException(constraintMessages);
        }
    }
}
