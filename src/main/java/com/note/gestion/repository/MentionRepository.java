package com.note.gestion.repository;

import com.note.gestion.model.Mention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentionRepository extends JpaRepository<Mention, Long> {
}
