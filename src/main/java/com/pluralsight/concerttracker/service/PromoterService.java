package com.pluralsight.concerttracker.service;

import com.pluralsight.concerttracker.data.PromoterRepository;
import com.pluralsight.concerttracker.models.Artist;
import com.pluralsight.concerttracker.models.Promoter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromoterService {

    private final PromoterRepository promoterRepository;
@Autowired
    public PromoterService(PromoterRepository promoterRepository) {
        this.promoterRepository = promoterRepository;
    }

    public List<Promoter> allPromoters(){
        return promoterRepository.findAll();
    }

    public Promoter getPromoterById(long id) {
        return promoterRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("No Promoter found with ID " + id));
    }
}
