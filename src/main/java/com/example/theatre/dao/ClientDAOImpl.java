package com.example.theatre.dao;

import com.example.theatre.entity.Client;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientDAOImpl implements ClientDAO {

    @Autowired
    private EntityManager em;

    @Override
    public Client addClient(Client client) {
        em.persist(client);
        em.flush();
        return client;
    }

    @Override
    public boolean checkClientByEmail(String email, String password) {
        String sql = "SELECT COUNT(*) != 0 from Client where email = :email and password = :password";
        Query query = em.createQuery(sql);
        query.setParameter("email", email);
        query.setParameter("password", password);
        return (boolean) query.getSingleResult();
    }

    @Override
    public boolean checkClientByPhone(String phone, String password) {
        String sql = "SELECT COUNT(*) != 0 from Client where phone = :phone and password = :password";
        Query query = em.createQuery(sql);
        query.setParameter("phone", phone);
        query.setParameter("password", password);
        return (boolean) query.getSingleResult();
    }

    @Override
    public Client getClientByEmail(String email) {
        String sql = "SELECT c FROM Client c where email = :email";
        Query query = em.createQuery(sql);
        query.setParameter("email", email);
        return (Client) query.getSingleResult();
    }

    @Override
    public Client getClientByPhone(String phone) {
        String sql = "SELECT c FROM Client c where phone = :phone";
        Query query = em.createQuery(sql);
        query.setParameter("phone", phone);
        return (Client) query.getSingleResult();
    }
}
