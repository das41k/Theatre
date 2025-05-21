package com.example.theatre.service;

import com.example.theatre.dao.RoleDAO;
import com.example.theatre.entity.Role;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDAO roleDAO;

    @Override
    @Transactional
    public Role findByRoleName(String roleName) {
        return roleDAO.findByName(roleName);
    }
}
