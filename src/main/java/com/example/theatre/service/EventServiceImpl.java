package com.example.theatre.service;

import com.example.theatre.dao.EventDAO;
import com.example.theatre.entity.Event;
import com.example.theatre.entity.EventType;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventDAO eventDAO;

    @Override
    @Transactional
    public List<Event> getAllEvents() {
        return eventDAO.getAllEvents();
    }

    @Override
    @Transactional
    public Event findById(Long id) {
        return eventDAO.findById(id);
    }

    @Override
    @Transactional
    public List<EventType> getAllTypes() {
        return eventDAO.getAllTypes();
    }

    @Override
    @Transactional
    public List<Event> getEventsByFilters(List<Integer> types, List<Integer> halls) {
        return eventDAO.getEventsByFilters(types, halls);
    }

    @Override
    @Transactional
    public List<Event> getEventsByFiltersType(List<Integer> types) {
        return eventDAO.getEventsByFiltersType(types);
    }

    @Override
    @Transactional
    public List<Event> getEventsByFiltersHall(List<Integer> filtersByHall) {
        return eventDAO.getEventsByFiltersHall(filtersByHall);
    }

    @Override
    @Transactional
    public List<Event> getEventsByDateFilters(List<String> dateFilters) {
        return eventDAO.getEventsByDateFilters(dateFilters);
    }

    @Override
    @Transactional
    public List<Event> getEventsByFiltersWithDates(List<Integer> types, List<Integer> halls, List<String> dateFilters) {
        return eventDAO.getEventsByFiltersWithDates(types, halls, dateFilters);
    }

    @Override
    @Transactional
    public List<Event> getEventsByFiltersTypeWithDates(List<Integer> types, List<String> dateFilters) {
        return eventDAO.getEventsByFiltersTypeWithDates(types, dateFilters);
    }

    @Override
    @Transactional
    public List<Event> getEventsByFiltersHallWithDates(List<Integer> halls, List<String> dateFilters) {
        return eventDAO.getEventsByFiltersHallWithDates(halls, dateFilters);
    }

}
