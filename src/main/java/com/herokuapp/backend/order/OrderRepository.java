package com.herokuapp.backend.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    OrderEntity getById(Long id);

    List<OrderEntity> getAllByClientIdOrderById(Long id);

    List<OrderEntity> getAllByDriverIdOrderById(Long id);

    List<OrderEntity> findAllByStatusEquals(OrderStatus status);
}