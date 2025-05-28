package com.example.theatre.controller;

import com.example.theatre.entity.Event;
import com.example.theatre.entity.EventType;
import com.example.theatre.entity.Place;
import com.example.theatre.service.EventService;
import com.example.theatre.service.HallService;
import com.example.theatre.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private HallService hallService;

    @Autowired
    private PlaceService placeService;

    @GetMapping
    public String events(Model model) {
        model.addAttribute("events", eventService.getAllEvents());
        model.addAttribute("types", eventService.getAllTypes());
        model.addAttribute("countHalls", hallService.getCountHalls());
        return "events";
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getEventImage(@PathVariable Long id) {
        Event event = eventService.findById(id);

        if (event == null || event.getImageData() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(event.getImageData());
    }

    @GetMapping("/filters")
    public String filters(Model model,
                          @RequestParam(value = "eventTypeId", required = false) List<Integer> filtersByType,
                          @RequestParam(value = "hallId", required = false) List<Integer> filtersByHall,
                          @RequestParam(value = "dateFilter", required = false) List<String> dateFilter) {


        if (filtersByType != null) {
            model.addAttribute("selectedEventTypes", filtersByType);
        }
        if (filtersByHall != null) {
            model.addAttribute("selectedHalls", filtersByHall);
        }
        if (dateFilter != null) {
            model.addAttribute("selectedDates", dateFilter);
        }
        model.addAttribute("types", eventService.getAllTypes());
        model.addAttribute("countHalls", hallService.getCountHalls());

        List<Event> events = null;


        boolean hasDateFilter = dateFilter != null && !dateFilter.isEmpty();

        if (filtersByType != null && !filtersByType.isEmpty() && filtersByHall != null && !filtersByHall.isEmpty()) {
            events = hasDateFilter ?
                    eventService.getEventsByFiltersWithDates(filtersByType, filtersByHall, dateFilter) :
                    eventService.getEventsByFilters(filtersByType, filtersByHall);
        }
        else if (filtersByType != null && !filtersByType.isEmpty()) {
            events = hasDateFilter ?
                    eventService.getEventsByFiltersTypeWithDates(filtersByType, dateFilter) :
                    eventService.getEventsByFiltersType(filtersByType);
        }
        else if (filtersByHall != null && !filtersByHall.isEmpty()) {
            events = hasDateFilter ?
                    eventService.getEventsByFiltersHallWithDates(filtersByHall, dateFilter) :
                    eventService.getEventsByFiltersHall(filtersByHall);
        }
        else if (hasDateFilter) {
            events = eventService.getEventsByDateFilters(dateFilter);
        }


        if (events == null) {
            model.addAttribute("events", eventService.getAllEvents());
        } else {
            model.addAttribute("events", events);
        }

        return "events";
    }

    @GetMapping("/{id}/page")
    public String eventPage(@PathVariable Long id, Model model) {
        Event event = eventService.findById(id);

        List<Object[]> eventPlaces = placeService.getPlaceStatusesForEvent(id);

        Long countRows = placeService.getCountRows(event.getHall());

        model.addAttribute("event",event);
        model.addAttribute("hall", event.getHall());
        model.addAttribute("rows", countRows);
        model.addAttribute("places", eventPlaces);
        return "event-page";
    }

}
