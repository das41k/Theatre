package com.example.theatre.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date_sale")
    private LocalDate dateSale;

    @Column(name = "cost")
    private int cost;

    @ManyToOne
    @JoinColumn(name = "Client_id")
    private Client client;

    @OneToOne
    @JoinColumn(name = "Place_id")
    private Place place;

    @ManyToOne
    @JoinColumn(name = "Event_id")
    private Event event;

    public int getId() {
        return id;
    }

    public LocalDate getDateSale() {
        return dateSale;
    }

    public int getCost() {
        return cost;
    }

    public Client getClient() {
        return client;
    }

    public Place getPlace() {
        return place;
    }

    public Event getEvent() {
        return event;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDateSale(LocalDate dateSale) {
        this.dateSale = dateSale;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
