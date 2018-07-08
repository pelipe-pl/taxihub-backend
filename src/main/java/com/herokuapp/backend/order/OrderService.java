package com.herokuapp.backend.order;

import com.herokuapp.backend.client.ClientServiceFacade;
import com.herokuapp.backend.driver.DriverServiceFacade;
import com.herokuapp.backend.email.EmailService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    List<OrderDto> getTakenByDriver(Long driverId) {
        return orderRepository.findAllByDriverIdAndStatusIn(driverId, Collections.singletonList(TAKEN))
                .stream().map(this::toDto)
                .collect(Collectors.toList());
    }

    Boolean hasOpenByClientId(Long clientId) {
        if (clientId != null && clientService.existsById(clientId))
            return orderRepository.existsByClient_IdAndStatusIn(clientId, Arrays.asList(OPEN, TAKEN));
        else
            throw new IllegalArgumentException("There is no client with this Id or it has not been provided");
    }

    void add(OrderDto orderDto) {
        if (!hasOpenByClientId(orderDto.getClientId())) {
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setClient(clientService.getById(orderDto.getClientId()));
            orderEntity.setFromLatitude(orderDto.getFromLatitude());
            orderEntity.setFromLongitude(orderDto.getFromLongitude());
            orderEntity.setFromAddress(orderDto.getFromAddress());
            orderEntity.setToLatitude(orderDto.getToLatitude());
            orderEntity.setToLongitude(orderDto.getToLongitude());
            orderEntity.setToAddress(orderDto.getToAddress());
            orderRepository.save(orderEntity);
        } else
            throw new IllegalArgumentException("The client with this Id already has an order with open or taken status.");
    }

    void setCanceled(Long id) {
        OrderEntity orderEntity = orderRepository.getById(id);
        OrderStatus status = orderEntity.getStatus();
        if (status == CANCELED)
            throw new IllegalArgumentException("This order is already CANCELED");
        if (status == TAKEN)
            throw new IllegalArgumentException("You cannot CANCEL this order. It is already TAKEN by the driver");
        if (status == CLOSED)
            throw new IllegalArgumentException("You cannot CANCEL this order. It is already CLOSED.");
        else {
            orderEntity.setEndTime(LocalDateTime.now());
            setStatusAndSave(orderEntity, CANCELED);
        }
    }

    void setTaken(Long id, Long driverId) {
        OrderEntity orderEntity = orderRepository.getById(id);
        OrderStatus status = orderEntity.getStatus();
        if (status == TAKEN)
            throw new IllegalArgumentException("This order is already TAKEN.");
        if (status == CLOSED)
            throw new IllegalArgumentException("This order is already CLOSED.");
        if (status == CANCELED)
            throw new IllegalArgumentException("This order is already CANCELED.");
        if (!driverService.existsById(driverId))
            throw new IllegalArgumentException("There is no driver with this Id.");
        if (orderRepository.countAllByDriver_IdAndStatus(driverId, TAKEN) >= 2)
            throw new IllegalArgumentException("This driver has maximum TAKEN orders.");
        if (driverService.getById(driverId).getSuspended()) {
            throw new IllegalArgumentException("This driver is suspended and can not take an order.");
        } else {
            orderEntity.setStartTime(LocalDateTime.now());
            orderEntity.setDriver(driverService.getById(driverId));
            setStatusAndSave(orderEntity, TAKEN);
        }
    }

    void setClosed(Long id) {
        OrderEntity orderEntity = orderRepository.getById(id);
        OrderStatus status = orderEntity.getStatus();
        if (status == OPEN) throw new IllegalArgumentException("The order with OPEN status cannot be closed.");
        if (status == CLOSED) throw new IllegalArgumentException("This order is already CLOSED.");
        if (status == CANCELED) throw new IllegalArgumentException("This order has been already CANCELED.");
        else {
            orderEntity.setEndTime(LocalDateTime.now());
            setStatusAndSave(orderEntity, CLOSED);
            emailService.sendCloseOrderEmail(orderEntity);
        }
    }

    private void setStatusAndSave(OrderEntity orderEntity, OrderStatus status) {
        orderEntity.setStatus(status);
        orderRepository.save(orderEntity);
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
                    orderEntity.getFromAddress(),
                    orderEntity.getToLatitude(),
                    orderEntity.getToLongitude(),
                    orderEntity.getToAddress(),
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
                    orderEntity.getFromAddress(),
                    orderEntity.getToLatitude(),
                    orderEntity.getToLongitude(),
                    orderEntity.getToAddress(),
                    orderEntity.getOpenTime(),
                    orderEntity.getStartTime(),
                    orderEntity.getEndTime());
    }
}