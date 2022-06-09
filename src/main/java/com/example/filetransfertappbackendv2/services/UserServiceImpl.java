package com.example.filetransfertappbackendv2.services;

import com.example.filetransfertappbackendv2.entities.User;
import com.example.filetransfertappbackendv2.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User findUserbyEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
