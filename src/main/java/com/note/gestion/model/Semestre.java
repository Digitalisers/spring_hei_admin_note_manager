package com.note.gestion.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Semestre {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long idSemestre;

    private String name;

    private LocalDate beginDate;

    private LocalDate finishDate;

    private Float average;

    private String mention;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Semestre semestre = (Semestre) o;
        return idSemestre != null && Objects.equals(idSemestre, semestre.idSemestre);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
