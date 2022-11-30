package com.note.gestion.repository;
import com.note.gestion.model.UserHei;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserHei, Long> {
    UserHei findByRef(String ref);
}
