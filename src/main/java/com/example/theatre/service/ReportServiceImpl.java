package com.example.theatre.service;

import com.example.theatre.dao.PlaceDAO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private PlaceDAO placeDAO;


    @Override
    @Transactional
    public List<Object[]> getPopularPlaces() {
        return placeDAO.getPopularPlaces();
    }
}
