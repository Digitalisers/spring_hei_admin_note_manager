package com.note.gestion.controller;

import com.note.gestion.mapper.UserMapper;
import com.note.gestion.model.UserHei;
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
    public List<UserHei> getUser(
            @RequestParam(name = "page", required = false)int page,
            @RequestParam(name = "page_size", required = false)int pageSize,
            @RequestParam(name = "last_name", required = false)String lastName,
            @RequestParam(name = "first_name", required = false)String firstName,
            @RequestParam(name = "ref", required = false)String ref
    ){
        return userService.getAllUsers(page, pageSize);
    }

    @GetMapping("/student/{ref}")
    public UserHei getUserWithAttribute(@PathVariable(name = "ref")String ref){
        return userService.getUserByAttributes(ref);
    }

    @PostMapping("/user")
    public UserHei insertUser(@RequestParam UserMapper newUser){
        return userService.insertUser(newUser);
    }

    @PutMapping("/user/{id_user}")
    public UserHei putModification(
            @PathVariable(name = "id_user")Long idUser,
            @RequestParam UserMapper userModified
    ){
        return userService.putModification(idUser, userModified);
    }

    @DeleteMapping("/user/{id_user}")
    public String deleteUser(@PathVariable(name = "id_user")Long idUser){
        return userService.deleteUser(idUser);
    }
}
