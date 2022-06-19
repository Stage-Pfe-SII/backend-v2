package com.example.filetransfertappbackendv2.repositories;

import com.example.filetransfertappbackendv2.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
