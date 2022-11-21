package com.note.gestion.model.validator;

import com.note.gestion.exception.BadRequestException;
import com.note.gestion.model.Course;
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

public class CourseValidator  implements Consumer<Course> {
    private final Validator validator;

    public void accept(List<Course> courses) {
        courses.forEach(this);
    }

    @Override public void accept(Course courses) {
        Set<ConstraintViolation<Course>> violations = validator.validate(courses);
        if (!violations.isEmpty()) {
            String constraintMessages = violations
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(". "));
            throw new BadRequestException(constraintMessages);
        }
    }
}
