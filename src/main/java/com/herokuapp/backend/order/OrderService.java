package com.herokuapp.backend.order;

import com.herokuapp.backend.client.ClientServiceFacade;
import com.herokuapp.backend.driver.DriverServiceFacade;
import com.herokuapp.backend.email.Email;
import com.herokuapp.backend.email.EmailService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.herokuapp.backend.order.OrderStatus.*;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ClientServiceFacade clientService;
    private final DriverServiceFacade driverService;
    private final EmailService emailService;

    public OrderService(OrderRepository orderRepository, ClientServiceFacade clientService, DriverServiceFacade driverService, EmailService emailService) {
        this.orderRepository = orderRepository;
        this.clientService = clientService;
        this.driverService = driverService;
        this.emailService = emailService;
    }

    OrderDto findById(Long id) {
        return toDto(orderRepository.getById(id));
    }

    List<OrderDto> findClientHistory(Long clientId) {
        return orderRepository.findAllByClientIdAndStatusIn(clientId, Arrays.asList(CANCELED, CLOSED))
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    List<OrderDto> findDriverHistory(Long driverId) {
        return orderRepository.findAllByDriverIdAndStatusIn(driverId, Arrays.asList(CANCELED, CLOSED))
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    List<OrderDto> findCorporationHistory(Long corporationId) {
        return orderRepository.findAllByDriver_CorporationIdAndStatusIn(corporationId, Arrays.asList(CANCELED, CLOSED))
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    List<OrderDto> findAllOpen() {
        return orderRepository.findAllByStatusEquals(OPEN)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    List<OrderDto> findAllOpenByCorporation(Long corporationId) {
        return orderRepository.findAllByDriver_CorporationIdAndStatusIn(corporationId, Collections.singletonList(OPEN))
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    OrderDto getOpenByClient(Long clientId) {
        if (hasOpenByClientId(clientId))
            return toDto(orderRepository.getByClient_IdAndStatusIn(clientId, Arrays.asList(OPEN, TAKEN)));
        else throw new IllegalArgumentException("The client with this Id has no open orders");
    }

    OrderDto getOpenByDriver(Long driverId) {
        return toDto(orderRepository.getFirstByDriverIdAndStatus(driverId, OPEN));
    }

    Boolean hasOpenByClientId(Long clientId) {
        if (clientId != null && clientService.existsById(clientId))
            return orderRepository.existsByClient_IdAndStatusIn(clientId, Arrays.asList(OPEN, TAKEN));
        else
            throw new IllegalArgumentException("There is no client with this Id or it has not been provided");
    }

    void add(OrderDto orderDto) {
        if (hasOpenByClientId(orderDto.getClientId())) {
            orderRepository.save(new OrderEntity(
                    clientService.getById(orderDto.getClientId()),
                    orderDto.getFromLatitude(),
                    orderDto.getFromLongitude(),
                    orderDto.getToLatitude(),
                    orderDto.getToLongitude()
            ));
        } else throw new IllegalArgumentException("The client with this Id already has an order with open or taken status");
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
        driverService.getById(driverId);
        setStatus(orderEntity, TAKEN);
        orderRepository.save(orderEntity);
    }

    void setClosed(Long id) {
        OrderEntity orderEntity = orderRepository.getById(id);
        orderEntity.setEndTime(LocalDateTime.now());
        setStatus(orderEntity, CLOSED);
        orderRepository.save(orderEntity);
        closeOrder(orderEntity);
    }

    private void setStatus(OrderEntity orderEntity, OrderStatus status) {
        orderEntity.setStatus(status);
    }

    private OrderDto toDto(OrderEntity orderEntity) {

        if (orderEntity.getDriver() != null) {

            return new OrderDto(
                    orderEntity.getId(),
                    orderEntity.getDriver().getId(),
                    orderEntity.getClient().getId(),
                    orderEntity.getStatus(),
                    orderEntity.getFromLatitude(),
                    orderEntity.getFromLongitude(),
                    orderEntity.getToLatitude(),
                    orderEntity.getToLongitude(),
                    orderEntity.getOpenTime(),
                    orderEntity.getStartTime(),
                    orderEntity.getEndTime());
        } else
            return new OrderDto(
                    orderEntity.getId(),
                    orderEntity.getClient().getId(),
                    orderEntity.getStatus(),
                    orderEntity.getFromLatitude(),
                    orderEntity.getFromLongitude(),
                    orderEntity.getToLatitude(),
                    orderEntity.getToLongitude(),
                    orderEntity.getOpenTime(),
                    orderEntity.getStartTime(),
                    orderEntity.getEndTime());
    }

    private void closeOrder(OrderEntity order) {
        StringBuilder content = new StringBuilder();
        content.append("Hello, thank you for the ride! Your trip is finished! See you next time! :)")
                .append("Your ride started at: ")
                .append(order.getStartTime())
                .append(" and finished at: ")
                .append(order.getEndTime());
        Email email = new Email();
        email.setTo(order.getClient().getEmail());
        email.setSubject("Thank you");
        email.setContent(content.toString());
        emailService.send(email);
    }
}