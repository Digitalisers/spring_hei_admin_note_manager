package com.note.gestion.controller;

import com.note.gestion.mapper.UserMapper;
import com.note.gestion.model.User;
import com.note.gestion.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping("/user")
    public List<User> getAllUser(
            @RequestParam(name = "page")int page,
            @RequestParam(name = "page_size")int pageSize
    ){
        return userService.getAllUsers(page, pageSize);
    }

    @PostMapping("/user")
    public User insertUser(@RequestParam UserMapper newUser){
        return userService.insertUser(newUser);
    }

    @PutMapping("/user/{id_user}")
    public User putModification(
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
