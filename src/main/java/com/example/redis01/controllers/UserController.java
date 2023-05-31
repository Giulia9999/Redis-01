package com.example.redis01.controllers;

import com.example.redis01.entities.UserJPA;
import com.example.redis01.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping
    public UserJPA createUser(@RequestBody UserJPA user) {
        return userService.create(user);
    }

    @GetMapping
    public void readAllUsers() {
        userService.readAll();
    }

    @GetMapping("/{id}")
    public void readOneUser(@PathVariable("id") Long id) {
        userService.readOne(id);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable("id") Long id, @RequestBody UserJPA user) {
        userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
    }
}

