package com.example.theatre.dao;

import com.example.theatre.entity.Hall;
import com.example.theatre.entity.Place;

import java.util.List;

public interface PlaceDAO {
    Long getCountRows(Hall hall);

    List<Object[]> getPlaceStatusesForEvent(Long eventId);
    List<Object[]> getPopularPlaces();
}
