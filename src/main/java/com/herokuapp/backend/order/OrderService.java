package com.herokuapp.backend.order;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.herokuapp.backend.order.OrderStatus.*;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    OrderDto findById(Long id) {
        return toDto(orderRepository.getById(id));
    }

    List<OrderDto> findByClientId(Long id) {
        return orderRepository.getAllByClientIdOrderById(id)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    List<OrderDto> findByDriverId(Long id) {
        return orderRepository.getAllByDriverIdOrderById(id)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    List<OrderDto> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    List<OrderDto> findAllByStatus(OrderStatus status) {
        return orderRepository.findAllByStatusEquals(status)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    void add(OrderDto orderDto) {
        orderRepository.save(new OrderEntity(
                orderDto.getClientId(),
                orderDto.getFromLatitude(),
                orderDto.getFromLongitude(),
                orderDto.getToLatitude(),
                orderDto.getToLongitude()
        ));
    }

    void setCanceled(Long id) {
        OrderEntity orderEntity = orderRepository.getById(id);
        orderEntity.setEndTime(LocalDateTime.now());
        setStatus(orderEntity, CANCELED);
        orderRepository.save(orderEntity);
    }

    void setTaken(Long id, Long driverId) {
        OrderEntity orderEntity = orderRepository.getById(id);
        orderEntity.setStartTime(LocalDateTime.now());
        orderEntity.setDriverId(driverId);
        setStatus(orderEntity, TAKEN);
        orderRepository.save(orderEntity);
    }

    void setClosed(Long id) {
        OrderEntity orderEntity = orderRepository.getById(id);
        orderEntity.setEndTime(LocalDateTime.now());
        setStatus(orderEntity, CLOSED);
        orderRepository.save(orderEntity);
    }

    private void setStatus(OrderEntity orderEntity, OrderStatus status) {
        orderEntity.setStatus(status);

    }

    private OrderDto toDto(OrderEntity orderEntity) {
        return new OrderDto(
                orderEntity.getId(),
                orderEntity.getDriverId(),
                orderEntity.getClientId(),
                orderEntity.getStatus(),
                orderEntity.getFromLatitude(),
                orderEntity.getFromLongitude(),
                orderEntity.getToLatitude(),
                orderEntity.getToLongitude(),
                orderEntity.getOpenTime(),
                orderEntity.getStartTime(),
                orderEntity.getEndTime());
    }
}