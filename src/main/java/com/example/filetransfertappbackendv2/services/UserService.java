package com.example.filetransfertappbackendv2.services;

import com.example.filetransfertappbackendv2.entities.User;

import java.util.List;

public interface UserService {
    User save(User user);
    List<User> getAllUsers();
    User findUserById(Long id);
    User findUserbyEmail(String email);
}
