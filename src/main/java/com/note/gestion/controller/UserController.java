package com.note.gestion.controller;

import com.note.gestion.mapper.UserMapper;
import com.note.gestion.model.UserHei;
import com.note.gestion.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
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

    @GetMapping("/student")
    public UserHei getUserWithAttribute(
            @RequestParam(name = "first_name")String firstName,
            @RequestParam(name = "last_name")String lastName,
            @RequestParam(name = "ref")String ref
    ){
        return userService.getUserByAttributes(firstName, lastName, ref);
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
