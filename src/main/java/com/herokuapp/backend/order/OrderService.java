package com.herokuapp.backend.order;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderDto findById(Integer id) {
        return toDto(orderRepository.getOne(id));
    }

    public List<OrderDto> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(orderEntity -> toDto(orderEntity))
                .collect(Collectors.toList());
    }

    public void add(OrderDto orderDto) {
        orderRepository.save(toEntity(orderDto));
    }


    //TODO
    public void cancel(){}



    public void remove(Integer id) {
        orderRepository.deleteById(id);
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


