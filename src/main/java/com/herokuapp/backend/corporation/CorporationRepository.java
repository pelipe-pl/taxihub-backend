package com.herokuapp.backend.corporation;

import com.herokuapp.backend.driver.DriverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorporationRepository extends JpaRepository<CorporationEntity, Integer> {
    DriverEntity findByEmail(String email);
    DriverEntity findByToken(String token);
}
