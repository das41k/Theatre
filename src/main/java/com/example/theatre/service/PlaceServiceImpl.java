package com.example.theatre.service;

import com.example.theatre.dao.PlaceDAO;
import com.example.theatre.entity.Hall;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceDAO placeDAO;

    @Override
    @Transactional
    public  Long getCountRows(Hall hall) {
        return placeDAO.getCountRows(hall);
    }
}
