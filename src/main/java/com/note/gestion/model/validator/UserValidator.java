package com.note.gestion.model.validator;

import com.note.gestion.exception.BadRequestException;
import com.note.gestion.model.User;
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
public class UserValidator implements Consumer<User> {
    private final Validator validator;

    public void accept(List<User> users) {
        users.forEach(this);
    }

    @Override public void accept(User users) {
        Set<ConstraintViolation<User>> violations = validator.validate(users);
        if (!violations.isEmpty()) {
            String constraintMessages = violations
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(". "));
            throw new BadRequestException(constraintMessages);
        }
    }
}
