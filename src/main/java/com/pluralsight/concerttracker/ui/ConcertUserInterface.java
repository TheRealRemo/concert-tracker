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

import java.util.ArrayList;
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
            System.out.println("\n _______                                      \n" + "(_______)                               _     \n" + " _       ___  ____   ____ _____  ____ _| |_   \n" + "| |     / _ \\|  _ \\ / ___) ___ |/ ___|_   _)  \n" + "| |____| |_| | | | ( (___| ____| |     | |_   \n" + " \\______)___/|_| |_|\\____)_____)_|      \\__)  \n" + "                                              \n" + " _______                _                     \n" + "(_______)              | |                    \n" + "    _  ____ _____  ____| |  _ _____  ____     \n" + "   | |/ ___|____ |/ ___) |_/ ) ___ |/ ___)    \n" + "   | | |   / ___ ( (___|  _ (| ____| |        \n" + "   |_|_|   \\_____|\\____)_| \\_)_____)_|        ");
            System.out.println("========================================================");
            System.out.println("1) Concerts");
            System.out.println("2) Search Concerts");
            System.out.println("3) Artists");
            System.out.println("4) Venues");
            System.out.println("5) Promoters");
            System.out.println("6) Reports");
            System.out.println("0) Quit");
            System.out.print("Choose: ");

            switch (scanner.nextInt()) {
                case 1 -> displayConcerts(scanner);
                case 2 -> displaySearchConcerts(scanner);
                case 3 -> displayArtists(scanner);
                case 4 -> displayVenues(scanner);
                case 5 -> displayPromoters(scanner);
                case 6 -> displayReports(scanner);
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
            System.out.println("\n===== Concerts =====");
            System.out.println("1) List All Concerts");
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
            System.out.println("====== Venues ======");
            System.out.println("1) List All Venues");
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


    public void displayArtists(Scanner scanner) {
        scanner.nextLine();
        boolean validOption = false;

        while (!validOption) {
            System.out.println("\n=== Artists ===");
            System.out.println("1) List All Artists");
            System.out.println("2) Add Artist");
            System.out.println("3) Find Artist By Genre");
            System.out.println("4) Find Artist By Name");
            System.out.println("5) Update Artist Genre");
            System.out.println("6) Delete Artist");
            System.out.println("0) Return To Main Menu");
            System.out.print("Please enter here: ");

            String input = scanner.nextLine();

            switch (input) {

                case "1" -> {
                    listAllArtists();
                    validOption = true;
                }

                case "2" -> {
                    addArtist(scanner);
                    validOption = true;
                }

                case "3" -> {
                    findArtistByGenre(scanner);
                    validOption = true;
                }

                case "4" -> {
                    findArtistByName(scanner);
                    validOption = true;
                }

                case "5" -> {
                    updateArtistGenre(scanner);
                    validOption = true;
                }

                case "6" -> {
                    deleteArtist(scanner);
                    validOption = true;
                }

                case "0" -> {
                    validOption = true;
                }

                default -> System.out.println("Invalid option.");
            }
        }
    }

    public void displayPromoters(Scanner scanner) {
        scanner.nextLine();
        boolean validOption = false;

        while (!validOption) {

            System.out.println("\n1) List All Promoters");
            System.out.println("2) Add Promoter");
            System.out.println("3) Find Promoter By Name");
            System.out.println("4) Delete Promoter");
            System.out.println("0) Return To Main Menu");
            System.out.print("Please enter here: ");

            String input = scanner.nextLine();

            switch (input) {

                case "1" -> {
                    listAllPromoters();
                    validOption = true;
                }

                case "2" -> {
                    addPromoter(scanner);
                    validOption = true;
                }

                case "3" -> {
                    findPromoterByName(scanner);
                    validOption = true;
                }

                case "4" -> {
                    deletePromoter(scanner);
                    validOption = true;
                }

                case "0" -> {
                    validOption = true;
                }

                default -> System.out.println("Invalid option.");
            }
        }
    }

    public void displaySearchConcerts(Scanner scanner) {
        scanner.nextLine();
        boolean validOption = false;

        while (!validOption) {

            System.out.println("\n=== Search Concerts ===");
            System.out.println("1) Search By Year");
            System.out.println("2) Search By Artist");
            System.out.println("3) Search By Venue");
            System.out.println("4) Search By City");
            System.out.println("5) Search By Maximum Price");
            System.out.println("6) Search By Price Range");
            System.out.println("7) Search By Maximum Price And Earliest Year");
            System.out.println("0) Return To Main Menu");
            System.out.print("Please enter here: ");

            String input = scanner.nextLine();

            switch (input) {

                case "1" -> {
                    findConcertsByYear(scanner);
                    validOption = true;
                }

                case "2" -> {
                    findConcertsByArtist(scanner);
                    validOption = true;
                }

                case "3" -> {
                    findConcertsByVenue(scanner);
                    validOption = true;
                }

                case "4" -> {
                    findConcertsByCity(scanner);
                    validOption = true;
                }

                case "5" -> {
                    findConcertsByMaxPrice(scanner);
                    validOption = true;
                }

                case "6" -> {
                    findConcertsByPriceRange(scanner);
                    validOption = true;
                }

                case "7" -> {
                    findConcertsByPriceAndYear(scanner);
                    validOption = true;
                }

                case "0" -> {
                    validOption = true;
                }

                default -> System.out.println("Invalid option.");
            }
        }
    }

    public void displayReports(Scanner scanner) {
        scanner.nextLine();
        boolean validOption = false;

        while (!validOption) {

            System.out.println("\n=== Reports ===");
            System.out.println("1) Revenue Per Venue");
            System.out.println("2) Busiest Venue and Artist");
            System.out.println("3) Average Ticket Price By Year");
            System.out.println("4) Capacity Report");
            System.out.println("0) Return To Main Menu");
            System.out.print("Please enter here: ");

            String input = scanner.nextLine();

            switch (input) {

                case "1" -> {
                    revenuePerVenue();
                    validOption = true;
                }

                case "2" -> {
                    busiestVenueAndArtist();
                    validOption = true;
                }

                case "3" -> {
                    averageTicketPriceByYear();
                    validOption = true;
                }

                case "4" -> {
                    capacityReport();
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

                System.out.println("-------------------------- \n" + "Concert " + concert.getId() + "\n-------------------------- \n" + "Artist Name: " + concert.getArtist().getName() + "\nVenue: " + concert.getVenue().getName() + ", " + concert.getVenue().getCity() + "\nPromoter: " + concert.getPromoter().getName() + "\nYear Held: " + concert.getYear() + "\nPrice: " + concert.getTicketPrice() + "\nTickets Sold: " + concert.getTicketsSold());
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

        System.out.print("Enter minimum capacity: ");
        int capacity = scanner.nextInt();

        List<Venue> venues = venueService.findByMinimumCapacity(capacity);

        if (venues.isEmpty()) {
            System.out.println("No venues found with capacity of at least " + capacity);
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

    public void findArtistByGenre(Scanner scanner) {

        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();

        List<Artist> artists = artistService.findByGenre(genre);

        if (artists.isEmpty()) {
            System.out.println("No artists found with genre: " + genre);
            return;
        }

        for (Artist artist : artists) {
            System.out.println("--------------------------");
            System.out.println("Artist " + artist.getId());
            System.out.println("--------------------------");
            System.out.println("Artist Name: " + artist.getName());
            System.out.println("Genre: " + artist.getGenre());
        }
    }

    public void findArtistByName(Scanner scanner) {

        System.out.print("Enter artist name: ");
        String name = scanner.nextLine();

        List<Artist> artists = artistService.findByName(name);

        if (artists.isEmpty()) {
            System.out.println("No artists found with name: " + name);
            return;
        }

        for (Artist artist : artists) {
            System.out.println("--------------------------");
            System.out.println("Artist " + artist.getId());
            System.out.println("--------------------------");
            System.out.println("Artist Name: " + artist.getName());
            System.out.println("Genre: " + artist.getGenre());
        }
    }

    public void findPromoterByName(Scanner scanner) {

        System.out.print("Enter promoter name: ");
        String name = scanner.nextLine();

        List<Promoter> promoters = promoterService.findByName(name);

        if (promoters.isEmpty()) {
            System.out.println("No promoters found with name: " + name);
            return;
        }

        for (Promoter promoter : promoters) {

            System.out.println("--------------------------");
            System.out.println("Promoter " + promoter.getId());
            System.out.println("--------------------------");
            System.out.println("Promoter Name: " + promoter.getName());
        }
    }

    public void findConcertsByYear(Scanner scanner) {

        System.out.print("Enter year: ");
        int year = scanner.nextInt();

        List<Concert> concerts = concertService.findByYear(year);

        if (concerts.isEmpty()) {
            System.out.println("No concerts found for year " + year);
            return;
        }

        for (Concert concert : concerts) {

            System.out.println("--------------------------");
            System.out.println("Concert " + concert.getId());
            System.out.println("--------------------------");
            System.out.println("Artist: " + concert.getArtist().getName());
            System.out.println("Venue: " + concert.getVenue().getName());
            System.out.println("City: " + concert.getVenue().getCity());
            System.out.println("Year: " + concert.getYear());
            System.out.println("Price: $" + concert.getTicketPrice());
            System.out.println("Tickets Sold: " + concert.getTicketsSold());
        }
    }

    public void findConcertsByArtist(Scanner scanner) {

        System.out.print("Enter artist name: ");
        String name = scanner.nextLine();

        List<Concert> concerts = concertService.findByArtistName(name);

        if (concerts.isEmpty()) {
            System.out.println("No concerts found for artist: " + name);
            return;
        }

        for (Concert concert : concerts) {

            System.out.println("--------------------------");
            System.out.println("Concert " + concert.getId());
            System.out.println("--------------------------");
            System.out.println("Artist: " + concert.getArtist().getName());
            System.out.println("Venue: " + concert.getVenue().getName());
            System.out.println("City: " + concert.getVenue().getCity());
            System.out.println("Year: " + concert.getYear());
            System.out.println("Price: $" + concert.getTicketPrice());
            System.out.println("Tickets Sold: " + concert.getTicketsSold());
        }
    }

    public void findConcertsByVenue(Scanner scanner) {

        System.out.print("Enter venue name: ");
        String venue = scanner.nextLine();

        List<Concert> concerts = concertService.findByVenueName(venue);

        if (concerts.isEmpty()) {
            System.out.println("No concerts found for venue: " + venue);
            return;
        }

        for (Concert concert : concerts) {

            System.out.println("--------------------------");
            System.out.println("Concert " + concert.getId());
            System.out.println("--------------------------");
            System.out.println("Artist: " + concert.getArtist().getName());
            System.out.println("Venue: " + concert.getVenue().getName());
            System.out.println("City: " + concert.getVenue().getCity());
            System.out.println("Year: " + concert.getYear());
            System.out.println("Price: $" + concert.getTicketPrice());
            System.out.println("Tickets Sold: " + concert.getTicketsSold());
        }
    }

    public void findConcertsByCity(Scanner scanner) {

        System.out.print("Enter city: ");
        String city = scanner.nextLine();

        List<Concert> concerts = concertService.findByCity(city);

        if (concerts.isEmpty()) {
            System.out.println("No concerts found in " + city);
            return;
        }

        for (Concert concert : concerts) {

            System.out.println("--------------------------");
            System.out.println("Concert " + concert.getId());
            System.out.println("--------------------------");
            System.out.println("Artist: " + concert.getArtist().getName());
            System.out.println("Venue: " + concert.getVenue().getName());
            System.out.println("City: " + concert.getVenue().getCity());
            System.out.println("Year: " + concert.getYear());
            System.out.println("Price: $" + concert.getTicketPrice());
            System.out.println("Tickets Sold: " + concert.getTicketsSold());
        }
    }

    public void findConcertsByMaxPrice(Scanner scanner) {

        System.out.print("Enter maximum ticket price: ");
        double price = scanner.nextDouble();

        List<Concert> concerts = concertService.findByMaxPrice(price);

        if (concerts.isEmpty()) {
            System.out.println("No concerts found at or below $" + price);
            return;
        }

        for (Concert concert : concerts) {

            System.out.println("--------------------------");
            System.out.println("Concert " + concert.getId());
            System.out.println("--------------------------");
            System.out.println("Artist: " + concert.getArtist().getName());
            System.out.println("Venue: " + concert.getVenue().getName());
            System.out.println("City: " + concert.getVenue().getCity());
            System.out.println("Year: " + concert.getYear());
            System.out.println("Price: $" + concert.getTicketPrice());
            System.out.println("Tickets Sold: " + concert.getTicketsSold());
        }
    }

    public void findConcertsByPriceRange(Scanner scanner) {

        System.out.print("Enter minimum price: ");
        double minPrice = scanner.nextDouble();

        System.out.print("Enter maximum price: ");
        double maxPrice = scanner.nextDouble();

        List<Concert> concerts = concertService.findByPriceRange(minPrice, maxPrice);

        if (concerts.isEmpty()) {
            System.out.println("No concerts found between $" + minPrice + " and $" + maxPrice);
            return;
        }

        for (Concert concert : concerts) {

            System.out.println("--------------------------");
            System.out.println("Concert " + concert.getId());
            System.out.println("--------------------------");
            System.out.println("Artist: " + concert.getArtist().getName());
            System.out.println("Venue: " + concert.getVenue().getName());
            System.out.println("City: " + concert.getVenue().getCity());
            System.out.println("Year: " + concert.getYear());
            System.out.println("Price: $" + concert.getTicketPrice());
            System.out.println("Tickets Sold: " + concert.getTicketsSold());
        }
    }

    public void findConcertsByPriceAndYear(Scanner scanner) {

        System.out.print("Enter maximum ticket price: ");
        double maxPrice = scanner.nextDouble();

        System.out.print("Enter earliest year: ");
        int earliestYear = scanner.nextInt();

        List<Concert> concerts = concertService.findByPriceAndYear(maxPrice, earliestYear);

        if (concerts.isEmpty()) {
            System.out.println("No concerts found matching the criteria.");
            return;
        }

        for (Concert concert : concerts) {

            System.out.println("--------------------------");
            System.out.println("Concert " + concert.getId());
            System.out.println("--------------------------");
            System.out.println("Artist: " + concert.getArtist().getName());
            System.out.println("Venue: " + concert.getVenue().getName());
            System.out.println("City: " + concert.getVenue().getCity());
            System.out.println("Year: " + concert.getYear());
            System.out.println("Price: $" + concert.getTicketPrice());
            System.out.println("Tickets Sold: " + concert.getTicketsSold());
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

    public void addArtist(Scanner scanner) {

        boolean validInput = false;

        while (!validInput) {

            try {

                System.out.print("Artist Name: ");
                String name = scanner.nextLine();

                System.out.print("Genre: ");
                String genre = scanner.nextLine();

                artistService.addArtist(new Artist(name, genre));

                System.out.println("Artist added!");

                validInput = true;

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void addPromoter(Scanner scanner) {

        boolean validInput = false;

        while (!validInput) {

            try {

                System.out.print("Promoter Name: ");
                String name = scanner.nextLine();

                promoterService.addPromoter(new Promoter(name));

                System.out.println("Promoter added!");

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

                System.out.println("Current tickets sold: " + concert.getTicketsSold());

                System.out.print("Enter updated number of tickets sold: ");
                int ticketsSold = scanner.nextInt();

                concert.setTicketsSold(ticketsSold);

                concertService.addConcert(concert);

                System.out.println("Tickets sold updated!");
                validInput = true;

            } catch (NotFoundException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void updateArtistGenre(Scanner scanner) {

        boolean validInput = false;

        while (!validInput) {

            try {

                listAllArtists();

                System.out.print("\nEnter Artist ID: ");
                long id = scanner.nextLong();
                scanner.nextLine();

                Artist artist = artistService.getArtistById(id);

                System.out.println("Current Genre: " + artist.getGenre());

                System.out.print("Enter updated genre: ");
                String genre = scanner.nextLine();

                artist.setGenre(genre);

                artistService.addArtist(artist);

                System.out.println("Artist genre updated!");

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

                System.out.println("Current ticket price: $" + concert.getTicketPrice());

                System.out.print("Enter updated ticket price: ");
                double ticketPrice = scanner.nextDouble();

                concert.setTicketPrice(ticketPrice);

                concertService.addConcert(concert);

                System.out.println("Ticket price updated!");
                validInput = true;

            } catch (NotFoundException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void updateVenueCapacity(Scanner scanner) {
        boolean validInput = false;

        while (!validInput) {

            try {

                listAllVenues();

                System.out.print("\nEnter Venue ID: ");
                long id = scanner.nextLong();

                Venue venue = venueService.getVenueById(id);

                System.out.println("Current capacity: " + venue.getCapacity());

                System.out.print("Enter updated capacity: ");
                int capacity = scanner.nextInt();

                venue.setCapacity(capacity);

                venueService.addVenue(venue);

                System.out.println("Venue capacity updated!");

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

    public void deleteVenue(Scanner scanner) {
        boolean validInput = false;

        while (!validInput) {

            try {

                listAllVenues();

                System.out.print("\nEnter Venue ID to delete (0 to cancel): ");
                long id = scanner.nextLong();

                if (id == 0) {
                    return;
                }

                Venue venue = venueService.getVenueById(id);

                System.out.println("Deleting venue: " + venue.getName());

                venueService.removeVenue(venue);

                System.out.println("Venue deleted successfully!");

                validInput = true;

            } catch (NotFoundException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void deleteArtist(Scanner scanner) {

        boolean validInput = false;

        while (!validInput) {

            try {

                listAllArtists();

                System.out.print("\nEnter Artist ID to delete (0 to cancel): ");
                long id = scanner.nextLong();

                if (id == 0) {
                    return;
                }

                Artist artist = artistService.getArtistById(id);

                System.out.println("Deleting artist: " + artist.getName());

                artistService.removeArtist(artist);

                System.out.println("Artist deleted successfully!");

                validInput = true;

            } catch (NotFoundException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void deletePromoter(Scanner scanner) {

        boolean validInput = false;

        while (!validInput) {

            try {

                listAllPromoters();

                System.out.print("\nEnter Promoter ID to delete (0 to cancel): ");
                long id = scanner.nextLong();

                if (id == 0) {
                    return;
                }

                Promoter promoter = promoterService.getPromoterById(id);

                System.out.println("Deleting promoter: " + promoter.getName());

                promoterService.removePromoter(promoter);

                System.out.println("Promoter deleted successfully!");

                validInput = true;

            } catch (NotFoundException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //print helper methods
    public void listAllConcerts() {
        System.out.println("There are currently " + concertService.count() + " Ongoing concerts.");
        System.out.println("Concerts: ");
        for (Concert concert : concertService.allConcerts()) {
            System.out.println("-------------------------- \n" + "Concert " + concert.getId() + "\n" + "-------------------------- \n" + "Artist Name: " + concert.getArtist().getName() + "\nVenue: " + concert.getVenue().getName() + ", " + concert.getVenue().getCity());
        }


    }

    public void listAllArtists() {
        System.out.println("There are currently " + artistService.count() + " Artists.");
        System.out.println("Artists: ");

        for (Artist artist : artistService.allArtists()) {
            System.out.println("-------------------------- \n" + "Artist " + artist.getId() + "\n-------------------------- \n" + "Artist Name: " + artist.getName() + "\nGenre: " + artist.getGenre());
        }
    }

    public void listAllVenues() {
        System.out.println("There are currently " + venueService.count() + " Venues.");
        System.out.println("Venues: ");

        for (Venue venue : venueService.allVenues()) {
            System.out.println("-------------------------- \n" + "Venue " + venue.getId() + "\n-------------------------- \n" + "Venue Name: " + venue.getName() + "\nCity: " + venue.getCity() + "\nCapacity: " + venue.getCapacity());
        }
    }

    public void listAllPromoters() {
        System.out.println("There are currently " + promoterService.count() + " Promoters.");
        System.out.println("Promoters: ");

        for (Promoter promoter : promoterService.allPromoters()) {
            System.out.println("-------------------------- \n" + "Promoter " + promoter.getId() + "\n-------------------------- \n" + "Promoter Name: " + promoter.getName());
        }
    }

    // report helper methods
    public void revenuePerVenue() {

        List<Venue> venues = venueService.allVenues();

        if (venues.isEmpty()) {
            System.out.println("No venue data available.");
            return;
        }

        for (Venue venue : venues) {

            Double revenue = concertService.revenueByVenue(venue);

            System.out.println("--------------------------");
            System.out.println("Venue: " + venue.getName());
            System.out.printf("Revenue: $%,.2f%n", revenue);
        }
    }

    public void busiestVenueAndArtist() {
        // Keep track of the venue with the most concerts
        Venue busiestVenue = null;
        long mostVenueConcerts = 0;
        // Check each venue and count how many concerts it hosts
        for (Venue venue : venueService.allVenues()) {

            long count = concertService.concertCountByVenue(venue);
            // Update venue with most concerts
            if (count > mostVenueConcerts) {
                mostVenueConcerts = count;
                busiestVenue = venue;
            }
        }
        // Keep track of the artist with the most concerts
        Artist busiestArtist = null;
        long mostArtistConcerts = 0;
        // Check each artist and count how many concerts they are in
        for (Artist artist : artistService.allArtists()) {

            long count = concertService.concertCountByArtist(artist);
            // Update artist with most concerts
            if (count > mostArtistConcerts) {
                mostArtistConcerts = count;
                busiestArtist = artist;
            }
        }

        System.out.println("=== Busiest Venue ===");

        if (busiestVenue != null) {
            System.out.println(busiestVenue.getName() + " (" + mostVenueConcerts + " concerts)");
        } else {
            System.out.println("No venue data available.");
        }

        System.out.println("\n=== Busiest Artist ===");

        if (busiestArtist != null) {
            System.out.println(busiestArtist.getName() + " (" + mostArtistConcerts + " concerts)");
        } else {
            System.out.println("No artist data available.");
        }
    }

    public void averageTicketPriceByYear() {
      // Keep track of years we've already reported on
        List<Integer> years = new ArrayList<>();
        // Go through every concert
        for (Concert concert : concertService.allConcerts()) {

            int year = concert.getYear();
            // Skip years that have already been processed
            if (years.contains(year)) {
                continue;
            }

            double average = concertService.averageTicketPriceByYear(year);

            System.out.printf("%d Average Ticket Price: $%,.2f%n", year, average);
          //so year won't print again
            years.add(year);
        }
    }

    public void capacityReport() {

    }
}

