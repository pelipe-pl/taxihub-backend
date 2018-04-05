package com.herokuapp.backend.client;

import com.herokuapp.backend.client.ClientEntity;
import com.herokuapp.backend.order.OrderDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {

    boolean existsByEmail(String email);

}
