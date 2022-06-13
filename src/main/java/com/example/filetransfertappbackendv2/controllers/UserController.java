package com.example.filetransfertappbackendv2.controllers;

import com.example.filetransfertappbackendv2.entities.Transfert;
import com.example.filetransfertappbackendv2.entities.User;
import com.example.filetransfertappbackendv2.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    List<User> getTransferts(){
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    User getUser(@PathVariable Long id){
        return userService.findUserById(id);
    }
}
