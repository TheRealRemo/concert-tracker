package com.pluralsight.concerttracker.service;

import com.pluralsight.concerttracker.data.ArtistRepository;
import com.pluralsight.concerttracker.models.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

    public final ArtistRepository artistRepository;

    @Autowired
    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public long count() {
        return artistRepository.count();
    }

    public List<Artist> allArtists() {
        return artistRepository.findAll();
    }

    public Artist getArtistById(long id) {
        return artistRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No Artist found with ID " + id));
    }

    public Artist addArtist(Artist artist) {
        return artistRepository.save(artist);
    }
    public void removeArtist(Artist artist) {

        try {
            artistRepository.delete(artist);
        }
        catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Cannot delete artist because concerts still reference it.");
        }
    }
    public List<Artist> findByGenre(String genre) {
        return artistRepository.findByGenre(genre);
    }

    public List<Artist> findByName(String name) {
        return artistRepository.findByName(name);
    }

}
