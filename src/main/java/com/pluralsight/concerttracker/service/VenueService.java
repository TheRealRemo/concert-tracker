package com.pluralsight.concerttracker.service;

import com.pluralsight.concerttracker.data.VenueRepository;
import com.pluralsight.concerttracker.models.Venue;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueService {
    private final VenueRepository venueRepository;

    public VenueService(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;


    }

    public List<Venue> allVenues() {
        return venueRepository.findAll();
    }

    public Venue getVenueById(long id) {
        return venueRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("No Venue found with ID " + id));
    }
}
