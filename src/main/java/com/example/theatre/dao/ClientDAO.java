package com.example.theatre.dao;

import com.example.theatre.entity.Client;

import java.util.List;

public interface ClientDAO {
    Client addClient(Client client);

    boolean checkClientByEmail(String email, String password);
    boolean checkClientByPhone(String phone, String password);
    Client getClientByEmail(String email);
    Client getClientByPhone(String phone);

}
