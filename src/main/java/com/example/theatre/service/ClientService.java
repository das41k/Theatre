package com.example.theatre.service;

import com.example.theatre.entity.Client;
import jakarta.transaction.Transactional;

public interface ClientService {
    Client addClient(Client client);
    Client checkLoginByPhone(String phone, String password);
    Client checkLoginByEmail(String email, String password);
}
