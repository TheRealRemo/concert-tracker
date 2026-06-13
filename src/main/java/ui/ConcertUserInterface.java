package ui;

import com.pluralsight.concerttracker.models.Concert;
import com.pluralsight.concerttracker.service.ConcertService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConcertUserInterface implements CommandLineRunner {
   private final ConcertService concertService;

    public ConcertUserInterface(ConcertService concertService) {
        this.concertService = concertService;
    }

    @Override
    public void run(String... args) throws Exception {
        concertService.seedIfEmpty();
    }


}
