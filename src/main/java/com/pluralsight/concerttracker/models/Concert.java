package com.pluralsight.concerttracker.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Concert {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private int year;
    private double ticketPrice;
    private int ticketsSold;

    public Concert() {
    }

    public Concert(int year, double ticketPrice, int ticketsSold) {
        this.year = year;
        this.ticketPrice = ticketPrice;
        this.ticketsSold = ticketsSold;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getTicketsSold() {
        return ticketsSold;
    }

    public void setTicketsSold(int ticketsSold) {
        this.ticketsSold = ticketsSold;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
