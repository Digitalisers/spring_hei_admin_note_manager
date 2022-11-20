package com.note.gestion.controller;

import com.note.gestion.mapper.UserMapper;
import com.note.gestion.model.User;
import com.note.gestion.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping("/user")
    public List<User> getUser(
            @RequestParam(name = "page", required = false)int page,
            @RequestParam(name = "page_size", required = false)int pageSize,
            @RequestParam(name = "last_name", required = false)String lastName,
            @RequestParam(name = "first_name", required = false)String firstName,
            @RequestParam(name = "ref", required = false)String ref
    ){
        return userService.getAllUsers(page, pageSize);
    }

    @GetMapping("/student")
    public User getUserWithAttribute(
            @RequestParam(name = "first_name")String firstName,
            @RequestParam(name = "last_name")String lastName,
            @RequestParam(name = "ref")String ref
    ){
        return userService.getUserByAttributes(firstName, lastName, ref);
    }

    @PostMapping("/user")
    public User insertUser(@RequestParam UserMapper newUser){
        return userService.insertUser(newUser);
    }

    @PutMapping("/user/{id_user}")
    public User putModification(
            @PathVariable(name = "id_user")String idUser,
            @RequestParam UserMapper userModified
    ){
        return userService.putModification(idUser, userModified);
    }

    @DeleteMapping("/user/{id_user}")
    public String deleteUser(@PathVariable(name = "id_user")String idUser){
        return userService.deleteUser(idUser);
    }
}
