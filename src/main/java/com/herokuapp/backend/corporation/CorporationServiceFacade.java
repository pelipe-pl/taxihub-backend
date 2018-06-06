package com.herokuapp.backend.corporation;

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

    public Long getByEmail(String email) {
        return corpRepository.findByEmail(email).getId();
    }
}
