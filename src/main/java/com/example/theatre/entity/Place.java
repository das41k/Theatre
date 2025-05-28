package com.example.theatre.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Place")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "number", nullable = false)
    private int number;

    @Column(name = "row", nullable = false)
    private int row;

    @Column(name = "place_view")
    private byte[] placeView;

    @ManyToOne
    @JoinColumn(name = "Hall_number", nullable = false)
    private Hall hall;

    @ManyToMany
    @JoinTable(
            name = "event_place",
            joinColumns = @JoinColumn(name = "place_id"),
            inverseJoinColumns = @JoinColumn(name ="event_id")
    )
    private List<Event> events;

    public int getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public int getRow() {
        return row;
    }

    public byte[] getPlaceView() {
        return placeView;
    }


    public Hall getHall() {
        return hall;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setPlaceView(byte[] placeView) {
        this.placeView = placeView;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Event> getEvents() {
        return events;
    }
}
