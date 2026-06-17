package com.pluralsight.concerttracker.data;

import com.pluralsight.concerttracker.models.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConcertRepository extends JpaRepository<Concert, Long> {
    @Query("SELECT c FROM Concert c WHERE c.year = :year")
    List<Concert> findByYear(@Param("year") int year);


    @Query(" SELECT c FROM Concert c  WHERE c.artist.name LIKE %:name% ")
    List<Concert> findByArtistName(@Param("name") String name);

    @Query("SELECT c FROM Concert c WHERE c.venue.name = :venue")
    List<Concert> findByVenueName(@Param("venue") String venue);

    @Query("SELECT c FROM Concert c WHERE c.venue.city = :city")
    List<Concert> findByCity(@Param("city") String city);

    @Query("SELECT c FROM Concert c WHERE c.ticketPrice <= :price")
    List<Concert> findByMaxPrice(@Param("price") double price);

    @Query("SELECT c FROM Concert c WHERE c.ticketPrice BETWEEN :minPrice AND :maxPrice")
    List<Concert> findByPriceRange(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);

    @Query(" SELECT c FROM Concert c " +
            " WHERE c.ticketPrice <= :maxPrice  AND c.year >= :earliestYear ")
    List<Concert> findByPriceAndYear(
            @Param("maxPrice") double maxPrice,
            @Param("earliestYear") int earliestYear);
}
