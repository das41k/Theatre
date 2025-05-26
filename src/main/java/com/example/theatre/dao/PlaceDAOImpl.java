package com.example.theatre.dao;

import com.example.theatre.entity.Hall;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PlaceDAOImpl implements PlaceDAO {

    @Autowired
    private EntityManager em;

    @Override
    public Long getCountRows(Hall hall) {
        String sql = "select COUNT(DISTINCT p.row) from Place p where p.hall = :hall";
        Query query = em.createQuery(sql);
        query.setParameter("hall", hall);
        return (Long) query.getSingleResult();
    }
}
