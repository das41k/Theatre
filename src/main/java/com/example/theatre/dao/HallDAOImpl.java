package com.example.theatre.dao;

import com.example.theatre.entity.Hall;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
