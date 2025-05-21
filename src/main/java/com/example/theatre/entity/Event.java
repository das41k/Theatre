package com.example.theatre.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "date")
    private LocalDate dateEvent;

    @ManyToOne
    @JoinColumn(name = "EventType_id", nullable = false)
    private EventType eventType;

    @ManyToOne
    @JoinColumn(name = "Hall_number")
    private Hall hall;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB", name = "intro")
    private byte[] imageData;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateEvent() {
        return dateEvent;
    }

    public EventType getEventType() {
        return eventType;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public Hall getHall() {
        return hall;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateEvent(LocalDate dateEvent) {
        this.dateEvent = dateEvent;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
}
