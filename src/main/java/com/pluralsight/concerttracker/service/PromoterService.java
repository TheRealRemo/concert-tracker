package com.pluralsight.concerttracker.service;

import com.pluralsight.concerttracker.data.PromoterRepository;
import com.pluralsight.concerttracker.models.Artist;
import com.pluralsight.concerttracker.models.Promoter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromoterService {

    private final PromoterRepository promoterRepository;
@Autowired
    public PromoterService(PromoterRepository promoterRepository) {
        this.promoterRepository = promoterRepository;
    }

    public long count() {
        return promoterRepository.count();
    }
    public List<Promoter> allPromoters(){
        return promoterRepository.findAll();
    }

    public Promoter getPromoterById(long id) {
        return promoterRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No Promoter found with ID " + id));
    }
    public List<Promoter> findByName(String name) {
        return promoterRepository.findByName(name);
    }
    public void addPromoter(Promoter promoter) {
        promoterRepository.save(promoter);
    }
    public void removePromoter(Promoter promoter) {

        try {
            promoterRepository.delete(promoter);
        }
        catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Cannot delete promoter because concerts still reference it.");
        }
    }
}
