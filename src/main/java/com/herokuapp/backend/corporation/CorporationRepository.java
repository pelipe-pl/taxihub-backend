package com.herokuapp.backend.corporation;

import com.herokuapp.backend.driver.DriverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CorporationRepository extends JpaRepository<CorporationEntity, Integer> {
    List<DriverEntity> findAllByCorporationId(Long corporationId);
}
