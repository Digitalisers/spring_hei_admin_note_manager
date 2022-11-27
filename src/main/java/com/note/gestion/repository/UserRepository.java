package com.note.gestion.repository;

import com.note.gestion.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndRefContainingIgnoreCase(String firstName, String lastName, String ref);
}
