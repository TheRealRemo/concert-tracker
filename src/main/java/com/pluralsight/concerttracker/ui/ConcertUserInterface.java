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

import java.util.List;
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
            System.out.println("1.) Concerts");
            System.out.println("2.) Search Concerts");
            System.out.println("3.) Artists");
            System.out.println("0) Quit");
            System.out.print("Choose: ");

            switch (scanner.nextInt()) {
                case 1 -> displayConcerts(scanner);
                case 4 -> displayVenues(scanner);
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
            System.out.println("4.) Update Ticket Price");
            System.out.println("5.) Update Tickets Sold");
            System.out.println("6.) Delete a Concert");
            System.out.println("0) Return To Main Menu");
            System.out.print("Please enter here: ");

            String input = scanner.nextLine();

            switch (input) {

                case "1" -> {
                    listAllConcerts();

                }
                case "2" -> {
                    findConcertByID(scanner);

                }
                case "3" -> {
                    addNewConcert(scanner);

                }
                case "4" -> {
                    updateTicketPrice(scanner);

                }
                case "5" -> {
                    updateTicketsSold(scanner);

                }
                case "6" -> {
                    deleteConcert(scanner);

                }

                case "0" -> {
                    validOption = true;
                }


                default -> System.out.println("Invalid option.");
            }
        }
    }

    public void displayVenues(Scanner scanner) {
        scanner.nextLine();
        boolean validOption = false;

        while (!validOption) {

            System.out.println("\n1) List All Venues");
            System.out.println("2) Add Venue");
            System.out.println("3) Find Venue By City");
            System.out.println("4) Find Venue By Name");
            System.out.println("5) Find Venue By Minimum Capacity");
            System.out.println("6) Update Venue Capacity");
            System.out.println("7) Delete Venue");
            System.out.println("0) Return To Main Menu");
            System.out.print("Please enter here: ");

            String input = scanner.nextLine();

            switch (input) {

                case "1" -> {
                    listAllVenues();
                }

                case "2" -> {
                    addNewVenue(scanner);

                }

                case "3" -> {
                    findVenueByCity(scanner);

                }

                case "4" -> {
                    findVenueByName(scanner);

                }

                case "5" -> {
                    findVenueByMinimumCapacity(scanner);

                }

                case "6" -> {
                    updateVenueCapacity(scanner);

                }

                case "7" -> {
                    deleteVenue(scanner);

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

    public void findVenueByCity(Scanner scanner) {
        System.out.print("Enter city: ");
        String city = scanner.nextLine();

        List<Venue> venues = venueService.findVenueByCity(city);

        if (venues.isEmpty()) {
            System.out.println("No venues found in " + city);
            return;
        }

        for (Venue venue : venues) {
            System.out.println("--------------------------");
            System.out.println("Venue " + venue.getId());
            System.out.println("--------------------------");
            System.out.println("Venue Name: " + venue.getName());
            System.out.println("City: " + venue.getCity());
            System.out.println("Capacity: " + venue.getCapacity());
        }
    }

    public void findVenueByName(Scanner scanner) {
        System.out.print("Enter venue name: ");
        String name = scanner.nextLine();

        List<Venue> venues = venueService.findVenueByName(name);

        if (venues.isEmpty()) {
            System.out.println("No venues found with name: " + name);
            return;
        }

        for (Venue venue : venues) {
            System.out.println("--------------------------");
            System.out.println("Venue " + venue.getId());
            System.out.println("--------------------------");
            System.out.println("Venue Name: " + venue.getName());
            System.out.println("City: " + venue.getCity());
            System.out.println("Capacity: " + venue.getCapacity());
        }
    }

    public void findVenueByMinimumCapacity(Scanner scanner) {
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

    public void addNewVenue(Scanner scanner) {

        boolean validInput = false;

        while (!validInput) {

            try {

                System.out.print("Venue Name: ");
                String name = scanner.nextLine();

                System.out.print("City: ");
                String city = scanner.nextLine();

                System.out.print("Capacity: ");
                int capacity = Integer.parseInt(scanner.nextLine());

                venueService.addVenue(new Venue(name, city, capacity));

                System.out.println("Venue added!");
                validInput = true;

            } catch (IllegalArgumentException e) {
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

            } catch (NotFoundException | IllegalArgumentException e) {
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

            } catch (NotFoundException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //delete helper methods

    public void deleteConcert(Scanner scanner) {
        boolean validInput = false;

        while (!validInput) {

            try {

                listAllConcerts();

                System.out.print("\nEnter Concert ID to delete: ");
                long id = scanner.nextLong();

                Concert concert = concertService.concertByID(id);

                concertService.removeConcert(concert);

                System.out.println("Concert " + concert.getId() + " deleted successfully!");

                validInput = true;

            } catch (NotFoundException e) {
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
