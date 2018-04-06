package com.herokuapp.backend.corporation;

import com.herokuapp.backend.driver.DriverDto;
import com.herokuapp.backend.driver.DriverEntity;
import com.herokuapp.backend.driver.DriverRepository;
import org.springframework.stereotype.Service;

@Service
public class CorporationService {

    private final DriverRepository repository;
    private final CorporationRepository corpRepository;

    public CorporationService(DriverRepository repository, CorporationRepository corpRepository) {
        this.repository = repository;
        this.corpRepository = corpRepository;
    }

    public DriverDto createDriver(DriverDto driver, Long corporationId) {
        final DriverEntity entity = new DriverEntity();
        entity.setName(driver.getName());
        entity.setSurname(driver.getSurname());
        entity.setEmail(driver.getEmail());
        entity.setCorporationId(corporationId);
        repository.save(entity);
        return driver;
    }

    public CorporationDto createCorporation(CorporationDto corporation){
        final CorporationEntity entity = new CorporationEntity();
        entity.setName(corporation.getName());
        entity.setEmail(corporation.getEmail());
        corpRepository.save(entity);
        return corporation;
    }
}
