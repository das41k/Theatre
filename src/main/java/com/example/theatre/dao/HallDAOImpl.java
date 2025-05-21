package com.example.theatre.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HallDAOImpl implements HallDAO {

    @Autowired
    private EntityManager em;

    @Override
    public Long getCountHalls() {
        String sql = "SELECT COUNT(h) FROM Hall h";
        Query query = em.createQuery(sql);
        return (Long) query.getSingleResult();
    }
}
