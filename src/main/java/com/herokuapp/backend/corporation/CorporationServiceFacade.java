package com.herokuapp.backend.corporation;

import javassist.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CorporationServiceFacade {
    private final CorporationRepository corpRepository;

    public CorporationServiceFacade(CorporationRepository corpRepository) {
        this.corpRepository = corpRepository;
    }

    public CorporationEntity getById(Long id) {
        return corpRepository.getById(id);
    }

    public Long getByEmail(String email) throws NotFoundException {
        if(corpRepository.existsByEmail(email)) return corpRepository.findByEmail(email).getId();
        else throw new NotFoundException("There is no Corporation with this e-mail.");
    }
}
