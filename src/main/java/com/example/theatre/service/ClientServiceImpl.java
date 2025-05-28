package com.example.theatre.service;

import com.example.theatre.PasswordHasher;
import com.example.theatre.dao.ClientDAO;
import com.example.theatre.entity.Client;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    EntityManager em;

    @Autowired
    private ClientDAO clientDAO;

    @Override
    @Transactional
    public Client addClient(Client client) {
        String hashedPassword = PasswordHasher.hashPassword(client.getPassword());
        client.setPassword(hashedPassword);
        return clientDAO.addClient(client);
    }

    @Override
    @Transactional
    public Client checkLoginByPhone(String phone, String password) {
        Client client = clientDAO.getClientByPhone(phone);
        if (client == null) {
            return null;
        }

        if (PasswordHasher.verifyPassword(password, client.getPassword())) {
            return client;
        }
        return null;
    }

    @Override
    @Transactional
    public Client checkLoginByEmail(String email, String password) {
        Client client = clientDAO.getClientByEmail(email);
        if (client == null) {
            return null;
        }

        if (PasswordHasher.verifyPassword(password, client.getPassword())) {
            return client;
        }
        return null;
    }


}
