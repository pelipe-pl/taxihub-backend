package com.herokuapp.backend.driver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<DriverEntity, Integer> {
    DriverEntity findByEmail(String email);

    DriverEntity getById(Long id);

    Boolean existsByEmail(String email);

    Boolean existsById(Long id);

    Boolean getStatusById(Long id);

    List<DriverEntity> findAllByCorporation_Id(Long corporationId);

    Boolean existsByToken(String token);

    DriverEntity getByToken(String token);

    DriverEntity getByPasswordResetToken(String token);
}
