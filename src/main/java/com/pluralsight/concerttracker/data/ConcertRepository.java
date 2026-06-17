package com.pluralsight.concerttracker.data;

import com.pluralsight.concerttracker.models.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConcertRepository extends JpaRepository <Concert, Long> {
    @Query("SELECT c FROM Concert c WHERE c.year = :year")
    List<Concert> findByYear(@Param("year") int year);
}
