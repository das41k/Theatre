package com.example.theatre.service;

import com.example.theatre.dao.EventDAO;
import com.example.theatre.dao.HallDAO;
import com.example.theatre.dao.PlaceDAO;
import com.example.theatre.entity.Event;
import com.example.theatre.entity.Hall;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceDAO placeDAO;

    @Autowired
    private HallDAO hallDAO;

    @Autowired
    private EventDAO eventDAO;

    @Override
    @Transactional
    public  Long getCountRows(Hall hall) {
        return placeDAO.getCountRows(hall);
    }

    @Override
    @Transactional
    public List<Object[]> getPlaceStatusesForEvent(Long eventId) {
        return placeDAO.getPlaceStatusesForEvent(eventId);
    }

}
