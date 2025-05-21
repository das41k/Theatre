package com.example.theatre.dao;

import com.example.theatre.entity.Event;
import com.example.theatre.entity.EventType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EventDAOImpl implements EventDAO {

    @Autowired
    private EntityManager em;

    @Override
    public List<Event> getAllEvents() {
        String sql = "select c from Event c";
        Query query = em.createQuery(sql);
        return query.getResultList();
    }

    @Override
    public Event findById(Long id) {
        return em.find(Event.class, id);
    }

    @Override
    public List<EventType> getAllTypes() {
        String sql = "select t from EventType t";
        Query query = em.createQuery(sql);
        return query.getResultList();
    }


    @Override
    public List<Event> getEventsByFilters(List<Integer> types, List<Integer> halls) {
       String sql = "select e from Event e where e.eventType.id IN (:types) and e.hall.id IN (:halls)";
       Query query = em.createQuery(sql);
       query.setParameter("types", types);
       query.setParameter("halls", halls);
       List<Event> events = query.getResultList();
       return events;
    }

    @Override
    public List<Event> getEventsByFiltersType(List<Integer> types) {
        String sql = "select e from Event e where e.eventType.id IN (:types)";
        Query query = em.createQuery(sql);
        query.setParameter("types", types);
        List<Event> events = query.getResultList();
        return events;
    }

    @Override
    public List<Event> getEventsByFiltersHall(List<Integer> halls) {
        String sql = "select e from Event e where e.hall.id IN (:halls)";
        Query query = em.createQuery(sql);
        query.setParameter("halls", halls);
        List<Event> events = query.getResultList();
        return events;
    }

    @Override
    public List<Event> getEventsByDateFilters(List<String> dateFilters) {
        String sql = "SELECT e FROM Event e WHERE ";
        sql += buildDateCondition(dateFilters);
        Query query = em.createQuery(sql);
        setDateParameters(query, dateFilters);
        return query.getResultList();
    }

    @Override
    public List<Event> getEventsByFiltersWithDates(List<Integer> types, List<Integer> halls, List<String> dateFilters) {
        String sql = "SELECT e FROM Event e WHERE e.eventType.id IN (:types) AND e.hall.id IN (:halls) AND ";
        sql += buildDateCondition(dateFilters);
        Query query = em.createQuery(sql);
        query.setParameter("types", types);
        query.setParameter("halls", halls);
        setDateParameters(query, dateFilters);
        return query.getResultList();
    }

    @Override
    public List<Event> getEventsByFiltersTypeWithDates(List<Integer> types, List<String> dateFilters) {
        String sql = "SELECT e FROM Event e WHERE e.eventType.id IN (:types) AND ";
        sql += buildDateCondition(dateFilters);
        Query query = em.createQuery(sql);
        query.setParameter("types", types);
        setDateParameters(query, dateFilters);
        return query.getResultList();
    }

    @Override
    public List<Event> getEventsByFiltersHallWithDates(List<Integer> halls, List<String> dateFilters) {
        String sql = "SELECT e FROM Event e WHERE e.hall.id IN (:halls) AND ";
        sql += buildDateCondition(dateFilters);
        Query query = em.createQuery(sql);
        query.setParameter("halls", halls);
        setDateParameters(query, dateFilters);
        return query.getResultList();
    }


    private String buildDateCondition(List<String> dateFilters) {
        List<String> conditions = new ArrayList<>();

        if (dateFilters.contains("today")) {
            conditions.add("e.dateEvent = :today");
        }
        if (dateFilters.contains("tomorrow")) {
            conditions.add("e.dateEvent = :tomorrow");
        }
        if (dateFilters.contains("week")) {
            conditions.add("e.dateEvent BETWEEN :today AND :endOfWeek");
        }
        if (dateFilters.contains("month")) {
            conditions.add("e.dateEvent BETWEEN :today AND :endOfMonth");
        }

        return "(" + String.join(" OR ", conditions) + ")";
    }

    private void setDateParameters(Query query, List<String> dateFilters) {
        LocalDate today = LocalDate.now();

        if (dateFilters.contains("today") || dateFilters.contains("week") || dateFilters.contains("month")) {
            query.setParameter("today", today);
        }
        if (dateFilters.contains("tomorrow")) {
            query.setParameter("tomorrow", today.plusDays(1));
        }
        if (dateFilters.contains("week")) {
            query.setParameter("endOfWeek", today.plusWeeks(1));
        }
        if (dateFilters.contains("month")) {
            query.setParameter("endOfMonth", today.plusMonths(1));
        }
    }
}
