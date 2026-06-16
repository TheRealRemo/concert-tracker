package com.pluralsight.concerttracker.service;

import com.pluralsight.concerttracker.data.VenueRepository;
import com.pluralsight.concerttracker.models.Venue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueService {
    private final VenueRepository venueRepository;

    @Autowired
    public VenueService(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;


    }

    public long count() {
        return venueRepository.count();
    }

    public List<Venue> allVenues() {
        return venueRepository.findAll();
    }

    public Venue getVenueById(long id) {
        return venueRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("No Venue found with ID " + id));
    }
    public List<Venue> findVenueByCity(String city) {
        return venueRepository.findByCity(city);
    }

    public List<Venue> findVenueByName(String name) {
        return venueRepository.findByName(name);
    }

    public Venue addVenue(Venue venue) {

        if (venue.getCapacity() < 0) {
            throw new IllegalArgumentException("Capacity cannot be less than 0");
        }

        return venueRepository.save(venue);
    }
}
