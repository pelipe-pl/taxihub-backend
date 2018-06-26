package com.herokuapp.backend.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    OrderEntity getById(Long id);

    List<OrderEntity> findAllByStatusEquals(OrderStatus status);

    List<OrderEntity> findAllByClientIdAndStatusIn(Long clientId, Collection<OrderStatus> status);

    List<OrderEntity> findAllByDriverIdAndStatusIn(Long driverId, Collection<OrderStatus> status);

    List<OrderEntity> findAllByDriver_CorporationIdAndStatusIn(Long corporationId, Collection<OrderStatus> status);

    OrderEntity getByClient_IdAndStatusIn(Long clientId, Collection<OrderStatus> status);

    OrderEntity getFirstByDriverIdAndStatus(Long driverId, OrderStatus status);

    Boolean existsByClient_IdAndStatusIn(Long clientId, List<OrderStatus> status);

    Integer countAllByDriver_IdAndStatus(Long driverId, OrderStatus status);
}