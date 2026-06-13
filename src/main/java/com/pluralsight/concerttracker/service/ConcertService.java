package com.pluralsight.concerttracker.service;

import com.pluralsight.concerttracker.data.ArtistRepository;
import com.pluralsight.concerttracker.data.ConcertRepository;
import com.pluralsight.concerttracker.data.PromoterRepository;
import com.pluralsight.concerttracker.data.VenueRepository;
import com.pluralsight.concerttracker.models.Artist;
import com.pluralsight.concerttracker.models.Venue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConcertService {

    private final ConcertRepository concertRepository;
    private final ArtistRepository artistRepository;
    private final PromoterRepository promoterRepository;
    private final VenueRepository venueRepository;

    @Autowired
    public ConcertService(ConcertRepository concertRepository, ArtistRepository artistRepository, PromoterRepository promoterRepository, VenueRepository venueRepository) {
        this.concertRepository = concertRepository;
        this.artistRepository = artistRepository;
        this.promoterRepository = promoterRepository;
        this.venueRepository = venueRepository;
    }


    public void seedIfEmpty() {

        if (concertRepository.count() > 0) {
            return;
        }
        Artist ytg = artistRepository.save(new Artist("Young The Giant", "Alternative Rock"));
        Artist beabadoobee = artistRepository.save(new Artist("beabadoobee", "Alternative Rock"));
        Artist mj = artistRepository.save(new Artist("Michael Jackson", "Pop"));
        Venue venue = venueRepository.save(new Venue("Metlife Stadium", "East Rutherford", 82500));
    }
}
