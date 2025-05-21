package com.example.theatre.service;

import com.example.theatre.dao.ClientDAO;
import com.example.theatre.entity.Client;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientDAO clientDAO;

    @Override
    @Transactional
    public Client addClient(Client client) {
        return clientDAO.addClient(client);
    }

    @Override
    @Transactional
    public Client checkLoginByPhone(String phone, String password) {
        if (!clientDAO.checkClientByPhone(phone,password)) {
            return null;
        }
        return clientDAO.getClientByPhone(phone);
    }

    @Override
    @Transactional
    public Client checkLoginByEmail(String email, String password) {
        if (!clientDAO.checkClientByEmail(email,password)) {
            return null;
        }
        return clientDAO.getClientByEmail(email);
    }
}
