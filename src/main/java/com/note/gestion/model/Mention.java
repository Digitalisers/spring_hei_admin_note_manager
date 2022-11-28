package com.note.gestion.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Mention {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long idMention;

    private String name;

    private Float startNote;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Mention mention = (Mention) o;
        return idMention != null && Objects.equals(idMention, mention.idMention);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
