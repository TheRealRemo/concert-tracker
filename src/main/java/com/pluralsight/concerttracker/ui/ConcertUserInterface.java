package com.pluralsight.concerttracker.ui;

import com.pluralsight.concerttracker.models.Concert;
import com.pluralsight.concerttracker.service.ConcertService;
import com.pluralsight.concerttracker.service.NotFoundException;
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
        concertService.concertSeedIfEmpty();
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
            System.out.println("1) Concerts");
            System.out.println("0) Quit");
            System.out.print("Choose: ");

            switch (scanner.nextInt()) {
                case 1 -> displayConcerts(scanner);
                case 0 -> running = false;
                default -> System.out.println("Unknown option.");
            }
        }


    }

    //nested menu helper methods
    public void displayConcerts(Scanner scanner) {
        scanner.nextLine();
        boolean validOption = false;

        while (!validOption) {

            System.out.println("\n1) List All Concerts");
            System.out.println("2.) View One Concert By ID");
            System.out.println("0) Return To Main Menu");
            System.out.print("Please enter here: ");

            String input = scanner.nextLine();

            switch (input) {

                case "1" -> {
                    listAllConcerts();
                    validOption = true;
                }
                case "2" -> {
                    findConcertByID(scanner);
                    validOption = true;
                }

                case "0" -> {
                    validOption = true;
                }


                default -> System.out.println("Invalid option.");
            }
        }
    }

    //find helper methods
    public void findConcertByID(Scanner scanner) {
        listAllConcerts();
        boolean idNotFound = true;

        while (idNotFound) {
            try {
                System.out.print("\n Please enter ID of Concert you would like to view: ");
                long id = scanner.nextLong();

                Concert concert = concertService.concertsByID(id);

                System.out.println("-------------------------- \n" + "Concert " + concert.getId()
                        + "\n-------------------------- \n" + "Artist Name: " + concert.getArtist().getName()
                        + "\nVenue: " + concert.getVenue().getName() + ", " + concert.getVenue().getCity()
                        + "\nPromoter: " + concert.getPromoter().getName() + "\nYear Held: " + concert.getYear()
                        + "\nPrice: " + concert.getTicketPrice() + "\nTickets Sold: " + concert.getTicketsSold());
                idNotFound = false;
            } catch (NotFoundException nfe) {
                System.out.println("\n" + nfe.getMessage());
            }
        }

    }

    //print helper methods
    public void listAllConcerts() {
        System.out.println("There are currently " + concertService.count() + " Ongoing concerts.");
        System.out.println("Concerts: ");
        for (Concert concert : concertService.allConcerts()) {
            System.out.println("-------------------------- \n" + "Concert " + concert.getId() + "\n" + "-------------------------- \n"
                    + "Artist Name: " + concert.getArtist().getName() + "\nVenue: " + concert.getVenue().getName() + ", " + concert.getVenue().getCity());
        }
    }

}
