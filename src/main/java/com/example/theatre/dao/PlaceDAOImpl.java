package com.example.theatre.dao;

import com.example.theatre.entity.Hall;
import com.example.theatre.entity.Place;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public List<Object[]> getPlaceStatusesForEvent(Long eventId) {
        String sql = "SELECT p.id, p.number, p.place_row, p.Hall_number, " +
                "ep.place_status " +
                "FROM Place p " +
                "JOIN event_place ep ON p.id = ep.place_id AND ep.event_id = ?1 " +
                "ORDER BY p.place_row, p.number";

        Query query = em.createNativeQuery(sql);
        query.setParameter(1, eventId);

        return query.getResultList();
    }

    @Override
    public List<Object[]> getPopularPlaces() {
        String sql = "SELECT p, COUNT(t) FROM Ticket t JOIN t.place p " +
                "GROUP BY p.id, p.hall.number, p.number, p.placeView, p.row " +
                "ORDER BY COUNT(t) DESC";
        Query query = em.createQuery(sql);
        query.setMaxResults(10);
        return query.getResultList();
    }

}
