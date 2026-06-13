package com.pluralsight.concerttracker.models;

import jakarta.persistence.*;

@Entity
public class Concert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int year;
    private double ticketPrice;
    private int ticketsSold;

    @ManyToOne
    private Artist artist;

    @ManyToOne
    private Venue venue;

    @ManyToOne
    private Promoter promoter;

    public Concert() {
    }

    public Concert(int year, double ticketPrice, int ticketsSold,
                   Artist artist, Venue venue, Promoter promoter) {
        this.year = year;
        this.ticketPrice = ticketPrice;
        this.ticketsSold = ticketsSold;
        this.artist = artist;
        this.venue = venue;
        this.promoter = promoter;
    }

    public Promoter getPromoter() {
        return promoter;
    }

    public void setPromoter(Promoter promoter) {
        this.promoter = promoter;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
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
