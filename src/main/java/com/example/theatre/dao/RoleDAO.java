package com.example.theatre.dao;

import com.example.theatre.entity.Role;

public interface RoleDAO {
    Role findByName(String name);
}
