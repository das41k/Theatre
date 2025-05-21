package com.example.theatre.dao;

import com.example.theatre.entity.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDAOImpl implements RoleDAO {

    @Autowired
    private EntityManager em;

    @Override
    public Role findByName(String name) {
        Query query = em.createQuery("select r from Role r where r.name=:name");
        query.setParameter("name", name);
        return (Role) query.getSingleResult();
    }
}
