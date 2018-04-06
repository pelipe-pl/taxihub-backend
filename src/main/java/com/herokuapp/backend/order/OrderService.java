package com.herokuapp.backend.order;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderDto findById(Long id) {
        return toDto(orderRepository.getById(id));
    }

    public List<OrderDto> findByClientId(Long id) {
        return orderRepository.getAllByClientId(id)
                .stream()
                .map(orderEntity -> toDto(orderEntity))
                .collect(Collectors.toList());
    }

    public List<OrderDto> findByDriverId(Long id) {
        return orderRepository.getAllByDriverId(id)
                .stream()
                .map(orderEntity -> toDto(orderEntity))
                .collect(Collectors.toList());
    }

    public List<OrderDto> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(orderEntity -> toDto(orderEntity))
                .collect(Collectors.toList());
    }

    public void add(OrderDto orderDto) {

        orderRepository.save(new OrderEntity(
                orderDto.getClientId(),
                orderDto.getFromLatitude(),
                orderDto.getFromLongitude(),
                orderDto.getToLatitude(),
                orderDto.getToLongitude()
        ));
    }


    public void setCanceled(Long id) {
        OrderEntity orderEntity = orderRepository.getById(id);
        orderEntity.setStatus(OrderStatus.CANCELED);
        orderEntity.setEndTime(LocalDateTime.now());
    }

    public void setTaken(Long id) {
        OrderEntity orderEntity = orderRepository.getById(id);
        orderEntity.setStatus(OrderStatus.TAKEN);
        orderEntity.setStartTime(LocalDateTime.now());
    }

    public void setClosed(Long id) {
        OrderEntity orderEntity = orderRepository.getById(id);
        orderEntity.setStatus(OrderStatus.CLOSED);
        orderEntity.setEndTime(LocalDateTime.now());
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

    private OrderEntity toEntity(OrderDto orderDto) {

        if (!orderRepository.existsById(orderDto.getId().intValue())) {
            return new OrderEntity(
                    orderDto.getDriverId(),
                    orderDto.getClientId(),
                    orderDto.getStatus(),
                    orderDto.getFromLatitude(),
                    orderDto.getFromLongitude(),
                    orderDto.getToLatitude(),
                    orderDto.getToLongitude(),
                    orderDto.getStartTime(),
                    orderDto.getEndTime());
        } else
            return new OrderEntity(
                    orderDto.getId(),
                    orderDto.getDriverId(),
                    orderDto.getClientId(),
                    orderDto.getStatus(),
                    orderDto.getFromLatitude(),
                    orderDto.getFromLongitude(),
                    orderDto.getToLatitude(),
                    orderDto.getToLongitude(),
                    orderDto.getStartTime(),
                    orderDto.getEndTime());
    }
}


