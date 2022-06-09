package com.example.filetransfertappbackendv2.repositories;

import com.example.filetransfertappbackendv2.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
}
