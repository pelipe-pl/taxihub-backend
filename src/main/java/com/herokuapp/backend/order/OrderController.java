package com.herokuapp.backend.order;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public void add(@RequestBody OrderDto orderDto) {
        orderService.add(orderDto);
    }

    @GetMapping("{id}")
    public OrderDto one(@PathVariable Long id) {
        return orderService.findById(id);
    }

    @GetMapping("open")
    public List<OrderDto> allOpen() {
        return orderService.findAllOpen();
    }

    @GetMapping("open/client/{clientId}")
    public OrderDto openByClientId(@PathVariable Long clientId) {
        return orderService.getOpenByClient(clientId);
    }

    @GetMapping("open/driver/{driverId}")
    public OrderDto openByDriverId(@PathVariable Long driverId) {
        return orderService.getOpenByDriver(driverId);
    }

    @GetMapping("open/corporation/{corporationId}")
    public List<OrderDto> openByCorporationId(@PathVariable Long corporationId) {
        return orderService.findAllOpenByCorporation(corporationId);
    }

    @GetMapping("hasopen/client/{clientId}")
    public Boolean hasOpenByClientId(@PathVariable Long clientId) {
        return orderService.hasOpenByClientId(clientId);
    }

    @GetMapping("history/client/{clientId}")
    public List<OrderDto> clientHistory(@PathVariable Long clientId) {
        return orderService.findClientHistory(clientId);
    }

    @GetMapping("history/driver/{driverId}")
    public List<OrderDto> driverHistory(@PathVariable Long driverId) {
        return orderService.findDriverHistory(driverId);
    }

    @GetMapping("history/corporation/{corporationId}")
    public List<OrderDto> corporationHistory(@PathVariable Long corporationId) {
        return orderService.findCorporationHistory(corporationId);
    }

    @PostMapping("{id}/taken/{driverId}")
    public void setTaken(@PathVariable Long id, @PathVariable Long driverId) {
        orderService.setTaken(id, driverId);
    }

    @PostMapping("{id}/cancelled")
    public void setCancelled(@PathVariable Long id) {
        orderService.setCancelled(id);
    }

    @PostMapping("{id}/closed")
    public void setClosed(@PathVariable Long id) {
        orderService.setClosed(id);
    }

    @GetMapping("taken/driver/{driverId}")
    public List<OrderDto> takenByDriverId(@PathVariable Long driverId) {
        return orderService.getTakenByDriver(driverId);
    }
}
