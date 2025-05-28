package com.example.theatre.service;

import com.example.theatre.entity.Hall;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Map;

public interface PlaceService {
    Long getCountRows(Hall hall);
    List<Object[]> getPlaceStatusesForEvent(Long eventId);
}
