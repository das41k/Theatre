package com.example.theatre.service;

import com.example.theatre.entity.Event;
import com.example.theatre.entity.EventType;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents();
    Event findById(Long id);
    List<EventType> getAllTypes();
    List<Event> getEventsByFilters(List<Integer> types, List<Integer> halls);
    List<Event> getEventsByFiltersType(List<Integer> types);
    List<Event> getEventsByFiltersHall(List<Integer> filtersByHall);
    List<Event> getEventsByDateFilters(List<String> dateFilters);
    List<Event> getEventsByFiltersWithDates(List<Integer> types, List<Integer> halls, List<String> dateFilters);
    List<Event> getEventsByFiltersTypeWithDates(List<Integer> types, List<String> dateFilters);
    List<Event> getEventsByFiltersHallWithDates(List<Integer> halls, List<String> dateFilters);
}
