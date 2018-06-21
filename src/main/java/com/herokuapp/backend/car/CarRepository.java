package com.herokuapp.backend.car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {

    Boolean existsByPlates(String plates);

    Boolean existsByPlatesAndDriverIdNot(String plates, Long driverId);

    Boolean existsByDriver_Id(Long driverId);

    CarEntity getByDriver_Id(Long id);
}
