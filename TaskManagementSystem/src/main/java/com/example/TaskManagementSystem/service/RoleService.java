package com.example.TaskManagementSystem.service;

import com.example.TaskManagementSystem.entity.RoleEntity;
import com.example.TaskManagementSystem.repository.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepo roleRepo;

    public RoleEntity getUserRole() {
        return roleRepo.findByName("ROLE_USER").get();
    }

}
