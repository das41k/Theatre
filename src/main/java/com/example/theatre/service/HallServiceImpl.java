package com.example.theatre.service;

import com.example.theatre.dao.HallDAO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HallServiceImpl implements HallService {

    @Autowired
    private HallDAO hallDAO;

    @Override
    @Transactional
    public Long getCountHalls() {
        return hallDAO.getCountHalls();
    }

}
