package com.example.theatre.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "Hall")
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int number;

    @Column(name = "capacity")
    private int capacity;

    public int getNumber() {
        return number;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
