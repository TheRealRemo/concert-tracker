package com.pluralsight.concerttracker.service;

import com.pluralsight.concerttracker.data.ArtistRepository;
import com.pluralsight.concerttracker.data.ConcertRepository;
import com.pluralsight.concerttracker.data.PromoterRepository;
import com.pluralsight.concerttracker.data.VenueRepository;
import com.pluralsight.concerttracker.models.Artist;
import com.pluralsight.concerttracker.models.Concert;
import com.pluralsight.concerttracker.models.Promoter;
import com.pluralsight.concerttracker.models.Venue;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public long count() {
        return concertRepository.count();
    }

    public List<Concert> allConcerts() {
        return concertRepository.findAll();
    }

    public Concert concertByID(long id) {
        return concertRepository.findById(id).orElseThrow(() -> new NotFoundException("No Concert found with ID " + id));
    }

    public List<Concert> findByYear(int year) {
        return concertRepository.findByYear(year);
    }

    public List<Concert> findByArtistName(String name) {
        return concertRepository.findByArtistName(name);
    }

    public List<Concert> findByVenueName(String venue) {
        return concertRepository.findByVenueName(venue);
    }

    public List<Concert> findByCity(String city) {
        return concertRepository.findByCity(city);
    }

    public List<Concert> findByMaxPrice(double price) {
        return concertRepository.findByMaxPrice(price);
    }

    public List<Concert> findByPriceRange(double minPrice, double maxPrice) {
        return concertRepository.findByPriceRange(minPrice, maxPrice);
    }

    public List<Concert> findByPriceAndYear(double maxPrice, int earliestYear) {
        return concertRepository.findByPriceAndYear(maxPrice, earliestYear);
    }

    //reports methods
    //non-primitive Double allows for additional error prevention by SQL
    public Double revenueByVenue(Venue venue) {

        Double revenue = concertRepository.revenueByVenue(venue);

        if (revenue == null) {
            return 0.0;
        }

        return revenue;
    }

    /// can be used to add and update
    public Concert addConcert(Concert concert) {

        if (concert.getTicketsSold() < 0) {
            throw new IllegalArgumentException("Tickets sold cannot be less than 0");
        }

        if (concert.getTicketPrice() < 0) {
            throw new IllegalArgumentException("Ticket price cannot be less than 0");
        }

        if (concert.getTicketsSold() > concert.getVenue().getCapacity()) {
            throw new IllegalArgumentException("Tickets cannot exceed venue capacity");
        }

        return concertRepository.save(concert);
    }


    public void removeConcert(Concert concert) {
        concertRepository.delete(concert);
    }


    public void concertSeedIfEmpty() {

        if (concertRepository.count() > 0) {
            return;
        }
        Artist youngTheGiant = artistRepository.save(new Artist("Young The Giant", "Alternative Rock"));

        Artist beabadoobee = artistRepository.save(new Artist("beabadoobee", "Alternative Rock"));

        Artist michaelJackson = artistRepository.save(new Artist("Michael Jackson", "Pop"));

        Artist malcomTodd = artistRepository.save(new Artist("Malcom Todd", "Indie Pop"));

        Artist harryStyles = artistRepository.save(new Artist("Harry Styles", "Pop"));

        Venue metlifeStadium = venueRepository.save(new Venue("Metlife Stadium", "East Rutherford", 82500));

        Venue madisonSquareGarden = venueRepository.save(new Venue("Madison Square Garden", "New York", 19800));

        Venue wellsFargo = venueRepository.save(new Venue("Wells Fargo Center", "Philadelphia", 21000));

        Promoter liveNation = promoterRepository.save(new Promoter("Live Nation"));

        Promoter aeg = promoterRepository.save(new Promoter("AEG Presents"));

        Promoter c3 = promoterRepository.save(new Promoter("C3 Presents"));

        Concert concert1 = concertRepository.save(new Concert(2026, 299.99, 17000, harryStyles, madisonSquareGarden, liveNation));

        Concert concert2 = concertRepository.save(new Concert(2026, 89.99, 15000, beabadoobee, wellsFargo, aeg));

        Concert concert3 = concertRepository.save(new Concert(2026, 125.00, 18000, youngTheGiant, madisonSquareGarden, c3));

        Concert concert4 = concertRepository.save(new Concert(2026, 450.00, 80000, michaelJackson, metlifeStadium, liveNation));

        Concert concert5 = concertRepository.save(new Concert(2026, 65.00, 12000, malcomTodd, wellsFargo, aeg));

        Concert concert6 = concertRepository.save(new Concert(2026, 275.00, 19500, harryStyles, madisonSquareGarden, c3));

        Concert concert7 = concertRepository.save(new Concert(2026, 110.00, 14000, youngTheGiant, wellsFargo, liveNation));

        Concert concert8 = concertRepository.save(new Concert(2026, 350.00, 78000, michaelJackson, metlifeStadium, aeg));

        Concert concert9 = concertRepository.save(new Concert(2026, 75.00, 13000, beabadoobee, madisonSquareGarden, c3));

        Concert concert10 = concertRepository.save(new Concert(2026, 59.99, 10000, malcomTodd, wellsFargo, liveNation));

        Concert concert11 = concertRepository.save(new Concert(2026, 225.00, 70000, harryStyles, metlifeStadium, aeg));

        Concert concert12 = concertRepository.save(new Concert(2026, 95.00, 16000, youngTheGiant, madisonSquareGarden, liveNation));
        ;

    }
}
