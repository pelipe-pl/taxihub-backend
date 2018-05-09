package com.herokuapp.backend.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {
    ClientEntity findByEmail(String email);

    ClientEntity getById(Long id);

    Boolean existsByEmail(String email);

    Boolean existsById(Long id);
}
