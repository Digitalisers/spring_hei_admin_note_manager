package com.note.gestion.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupHei implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long idGroup;

    private String name;
    private String ref;

    @CreationTimestamp
    private Instant creationDatetime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        GroupHei groupHei = (GroupHei) o;
        return idGroup != null && Objects.equals(idGroup, groupHei.idGroup);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
