package com.example.filetransfertappbackendv2.controllers;

import com.example.filetransfertappbackendv2.entities.Transfert;
import com.example.filetransfertappbackendv2.entities.User;
import com.example.filetransfertappbackendv2.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    List<User> getTransferts(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    User getUser(@PathVariable Long id){
        return userService.findUserById(id);
    }

    @PostMapping
    User createUser(@RequestBody User user){
        return userService.save(user);
    }

}
