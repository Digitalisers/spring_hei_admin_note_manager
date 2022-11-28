package com.note.gestion.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Evaluation {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long idEvaluation;

    private LocalDate dateExamen;

    @ManyToOne
    private Course course;

    @ManyToOne
    private Semestre semestre;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Evaluation that = (Evaluation) o;
        return idEvaluation != null && Objects.equals(idEvaluation, that.idEvaluation);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
