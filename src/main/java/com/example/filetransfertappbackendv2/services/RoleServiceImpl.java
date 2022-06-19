package com.example.filetransfertappbackendv2.services;

import com.example.filetransfertappbackendv2.entities.Role;
import com.example.filetransfertappbackendv2.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;


    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }
}
