package com.note.gestion.repository;

import com.note.gestion.model.GroupHei;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<GroupHei, Long> {
}
