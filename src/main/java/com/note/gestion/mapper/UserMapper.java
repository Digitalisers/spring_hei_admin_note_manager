package com.note.gestion.mapper;

import com.note.gestion.model.UserHei;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class UserMapper {
    private Long idUser;

    private String firstName;

    private String lastName;

    private String email;

    private String ref;

    private UserHei.Status status;

    private String phone;

    private LocalDate birthDate;

    private Instant entranceDatetime;

    private UserHei.Sex sex;

    private String address;

    private UserHei.Role role;

    private Long idGroupe;
}
