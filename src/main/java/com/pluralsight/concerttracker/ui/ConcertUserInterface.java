package com.pluralsight.concerttracker.ui;

import com.pluralsight.concerttracker.models.Concert;
import com.pluralsight.concerttracker.service.ConcertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConcertUserInterface implements CommandLineRunner {
   private final ConcertService concertService;

   @Autowired
    public ConcertUserInterface(ConcertService concertService) {
        this.concertService = concertService;
    }

    @Override
    public void run(String... args) throws Exception {
        concertService.seedIfEmpty();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("\n _______                                      \n" +
                    "(_______)                               _     \n" +
                    " _       ___  ____   ____ _____  ____ _| |_   \n" +
                    "| |     / _ \\|  _ \\ / ___) ___ |/ ___|_   _)  \n" +
                    "| |____| |_| | | | ( (___| ____| |     | |_   \n" +
                    " \\______)___/|_| |_|\\____)_____)_|      \\__)  \n" +
                    "                                              \n" +
                    " _______                _                     \n" +
                    "(_______)              | |                    \n" +
                    "    _  ____ _____  ____| |  _ _____  ____     \n" +
                    "   | |/ ___|____ |/ ___) |_/ ) ___ |/ ___)    \n" +
                    "   | | |   / ___ ( (___|  _ (| ____| |        \n" +
                    "   |_|_|   \\_____|\\____)_| \\_)_____)_|        ");
            System.out.println("========================================================");
            System.out.println("1) List all concerts");
            System.out.println("0) Quit");
            System.out.print("Choose: ");

            switch (scanner.nextInt()) {
                case 1 -> listAllConcerts();

                case 0 -> running = false;
                default -> System.out.println("Unknown option.");
            }
    }


}
    public void listAllConcerts(){
        System.out.println("There are currently " + concertService.count() + " Ongoing concerts.");
        for (Concert concert : concertService.allConcerts()) {
            System.out.println("Concerts: ");
            System.out.println("- " + concert.getId() + "." );
        }

    }
}
