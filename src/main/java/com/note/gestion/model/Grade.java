package com.note.gestion.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Grade implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long idGrade;

    private Float average;

    @ManyToOne
    private Evaluation evaluation;

    @ManyToOne
    private UserHei student;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Grade grade = (Grade) o;
        return idGrade != null && Objects.equals(idGrade, grade.idGrade);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
