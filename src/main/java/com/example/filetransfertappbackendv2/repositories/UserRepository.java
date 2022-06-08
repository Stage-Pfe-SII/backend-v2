package com.example.filetransfertappbackendv2.repositories;

import com.example.filetransfertappbackendv2.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
