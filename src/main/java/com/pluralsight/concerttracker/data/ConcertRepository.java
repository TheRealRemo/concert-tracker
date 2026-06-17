package com.pluralsight.concerttracker.data;

import com.pluralsight.concerttracker.models.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConcertRepository extends JpaRepository<Concert, Long> {
    @Query("SELECT c FROM Concert c WHERE c.year = :year")
    List<Concert> findByYear(@Param("year") int year);

    //triple text block feature allows for clearer queries
    //rather than use CONCAT '%' on both sides of the String/Object field
    @Query("""
            SELECT c
            FROM Concert c
            WHERE c.artist.name LIKE %:name%
            """)
    List<Concert> findByArtistName(@Param("name") String name);

    @Query("SELECT c FROM Concert c WHERE c.venue.name = :venue")
    List<Concert> findByVenueName(@Param("venue") String venue);
}
