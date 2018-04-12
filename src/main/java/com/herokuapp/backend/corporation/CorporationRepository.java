package com.herokuapp.backend.corporation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorporationRepository extends JpaRepository<CorporationEntity, Integer> {
    List<DriverEntity> findAllByCorporationId(Long corporationId);
    CorporationEntity findByEmail(String email);

    CorporationEntity getById(Long id);

}
