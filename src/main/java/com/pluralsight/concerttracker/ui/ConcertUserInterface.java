package com.pluralsight.concerttracker.ui;

import com.pluralsight.concerttracker.models.Artist;
import com.pluralsight.concerttracker.models.Concert;
import com.pluralsight.concerttracker.models.Promoter;
import com.pluralsight.concerttracker.models.Venue;
import com.pluralsight.concerttracker.service.*;
import com.pluralsight.concerttracker.service.IllegalArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConcertUserInterface implements CommandLineRunner {
    private final ConcertService concertService;
    private final ArtistService artistService;
    private final VenueService venueService;
    private final PromoterService promoterService;


    public ConcertUserInterface(ConcertService concertService, ArtistService artistService, VenueService venueService, PromoterService promoterService) {
        this.concertService = concertService;
        this.artistService = artistService;
        this.venueService = venueService;
        this.promoterService = promoterService;
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
            System.out.println("3.) Add a Concert");
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
                case "3" -> {
                    addNewConcert(scanner);
                    validOption = true;
                }
                case "4" -> {
                    updateTicketPrice(scanner);
                    validOption = true;
                }
                case "5" -> {
                    updateTicketsSold(scanner);
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

                Concert concert = concertService.concertByID(id);

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

    //add helper methods
    public void addNewConcert(Scanner scanner) {
        boolean validInput = false;

        while (!validInput) {
            try {
                listAllArtists();
                System.out.print("Choose Artist ID: ");
                long artistID = scanner.nextLong();
                Artist artist = artistService.getArtistById(artistID);

                listAllVenues();
                System.out.print("Choose Venue ID: ");
                long venueID = scanner.nextLong();
                Venue venue = venueService.getVenueById(venueID);

                listAllPromoters();
                System.out.print("Choose Promoter ID: ");
                long promoterID = scanner.nextLong();
                Promoter promoter = promoterService.getPromoterById(promoterID);

                System.out.print("Year: ");
                int year = scanner.nextInt();

                System.out.print("Ticket Price: ");
                double ticketPrice = scanner.nextDouble();

                System.out.print("Tickets Sold: ");
                int ticketsSold = scanner.nextInt();


                concertService.addConcert(new Concert(year, ticketPrice, ticketsSold, artist, venue, promoter));
                System.out.println("New Concert Added!");
                validInput = true;
            } catch (NotFoundException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //update helper methods
    public void updateTicketsSold(Scanner scanner) {
        listAllConcerts();

        boolean validInput = false;

        while (!validInput) {

            try {

                listAllConcerts();

                System.out.print("\nEnter Concert ID: ");
                long id = scanner.nextLong();

                Concert concert = concertService.concertByID(id);

                System.out.println("Current tickets sold: "
                        + concert.getTicketsSold());

                System.out.print("Enter updated number of tickets sold: ");
                int ticketsSold = scanner.nextInt();

                concert.setTicketsSold(ticketsSold);

                concertService.updateConcert(concert);

                System.out.println("Tickets sold updated!");
                validInput = true;

            }
            catch (NotFoundException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    public void updateTicketPrice(Scanner scanner) {
        listAllConcerts();

        boolean validInput = false;

        while (!validInput) {

            try {

                listAllConcerts();

                System.out.print("\nEnter Concert ID: ");
                long id = scanner.nextLong();

                Concert concert = concertService.concertByID(id);

                System.out.println("Current ticket price: $"
                        + concert.getTicketPrice());

                System.out.print("Enter updated ticket price: ");
                double ticketPrice = scanner.nextDouble();

                concert.setTicketPrice(ticketPrice);

                concertService.updateConcert(concert);

                System.out.println("Ticket price updated!");
                validInput = true;

            }
            catch (NotFoundException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
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

    public void listAllArtists() {
        System.out.println("There are currently " + artistService.count() + " Artists.");
        System.out.println("Artists: ");

        for (Artist artist : artistService.allArtists()) {
            System.out.println("-------------------------- \n" +
                    "Artist " + artist.getId() +
                    "\n-------------------------- \n" +
                    "Artist Name: " + artist.getName() +
                    "\nGenre: " + artist.getGenre());
        }
    }

    public void listAllVenues() {
        System.out.println("There are currently " + venueService.count() + " Venues.");
        System.out.println("Venues: ");

        for (Venue venue : venueService.allVenues()) {
            System.out.println("-------------------------- \n" +
                    "Venue " + venue.getId() +
                    "\n-------------------------- \n" +
                    "Venue Name: " + venue.getName() +
                    "\nCity: " + venue.getCity() +
                    "\nCapacity: " + venue.getCapacity());
        }
    }

    public void listAllPromoters() {
        System.out.println("There are currently " + promoterService.count() + " Promoters.");
        System.out.println("Promoters: ");

        for (Promoter promoter : promoterService.allPromoters()) {
            System.out.println("-------------------------- \n" +
                    "Promoter " + promoter.getId() +
                    "\n-------------------------- \n" +
                    "Promoter Name: " + promoter.getName());
        }
    }
}
