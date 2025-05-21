package com.example.theatre.service;

import com.example.theatre.entity.Role;

public interface RoleService {
    Role findByRoleName(String roleName);
}
