# Concert Tracker

## Project Description

Concert Tracker is a Java console application designed to help users organize and manage information about concerts,
artists, venues, and promoters. It is intended for event organizers or anyone who wants to keep track of concert details
in a structured way.

The application allows users to add, view, update, search, and delete records for concerts, artists, venues, and
promoters. Users can search concerts by criteria such as year, artist, venue, city, and ticket price. The program also
generates reports, including revenue by venue, busiest venues and artists, average ticket prices by year, and venue
capacity usage.

The purpose of Concert Tracker is to provide an easy way to manage concert data and gain useful insights from it.

## User Stories

- As a user, I want to add concerts so that I can keep track of upcoming and past events.

- As a user, I want to view all concerts and their details so that I can quickly see what events are in the system.

- As a user, I want to update ticket prices and tickets sold so that concert information remains accurate.

- As a user, I want to search for concerts by year, artist, venue, city, or ticket price so that I can easily find
  specific events.

- As a user, I want to manage artists by adding, updating, searching, and deleting them so that artist information stays
  organized.

- As a user, I want to manage venues and promoters so that I can maintain accurate information about where concerts are
  held and who organizes them.

- As a user, I want to generate reports such as revenue by venue, busiest venues and artists, average ticket prices, and
  venue capacity usage so that I can gain insights into concert performance.

- As a user, I want the application to prevent invalid data and display helpful messages when errors occur so that I can
  safely manage concert information without crashing the program.

- As a user, I want to delete concerts before deleting related artists, venues, or promoters so that database
  relationships remain consistent.

- As a user, I want the application to display clear menus and allow me to cancel operations so that I can navigate the
  system easily.

### Prerequisites

- IntelliJ IDEA: Ensure you have IntelliJ IDEA installed, which you can download
  from [here](https://www.jetbrains.com/idea/download/).
- Java SDK: Make sure Java SDK is installed and configured in IntelliJ.

### Running the Application in IntelliJ

Follow these steps to get your application running within IntelliJ IDEA:

1. Open IntelliJ IDEA.
2. Select "Open" and navigate to the directory where you cloned or downloaded the project.
3. After the project opens, wait for IntelliJ to index the files and set up the project.
4. Find the main class with the `public static void main(String[] args)` method.
5. Right-click on the file and select 'Run 'ConcertTrackerApplication.main()'' to start the application.

## Technologies Used

- Java Amazon Corretto 17
- Spring Data JPA
- MySQL Driver

## Demo



![Application Screenshot](https://i.imgur.com/GyOpVs7.gif)
![Application Screenshot](https://i.imgur.com/ZObpnlt.gif)


## Future Work

- Additional user validation and error handling to improve the user experience and prevent invalid input.
- More consistent menus and user interface formatting across the application.
- Bug fixes and code refactoring to improve maintainability and readability.
- A dashboard screen that provides a summary of the system, including the number of concerts, artists, and venues, total
  revenue, and the busiest venue.
- Additional sorting options, such as listing concerts by ticket price (lowest to highest) or by year.
- A "Nearly Sold Out" report that highlights concerts that have reached at least 90% of venue capacity but are not yet
  sold out.
- Support for full concert dates instead of only storing the year, along with the ability to search for concerts within
  a date range.
- Introduction of additional relationships, such as a Tour entity that groups multiple concerts together and allows
  users to view all concerts associated with a particular tour.
- Enhanced reporting features.

## Resources

List resources such as tutorials, articles, or documentation that helped you during the project.

- [Raymond Maroun Concert Tracker Reference Guide](https://raymaroun.github.io/yearup-java-visuals/week-10/exercises/concert-tracker-workshop.html)
- [Annotations & Query Reference](https://raymaroun.github.io/yearup-java-visuals/week-10/reference/annotations-reference.html)
- [The Service Layer](https://raymaroun.github.io/yearup-java-visuals/week-10/visuals/15-service-layer.html)
- [Exception Handling GTP Conversation](https://chatgpt.com/s/t_6a337d028fec8191b3e3a8b25ad84b2d)
- [Exception Handling Proved Smart GPT Conversation](https://chatgpt.com/s/t_6a337da545588191a406d6cf0d1809dc)


## Thanks


- Thank you to Raymond for being a great mentor. 
- Thank you to Ricky for being you.
- Thank you to Adam for being Adam.
 