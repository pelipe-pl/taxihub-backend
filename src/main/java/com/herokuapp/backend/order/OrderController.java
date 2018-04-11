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

    @GetMapping("history/client/{clientId}")
    public List<OrderDto> clientHistory(@PathVariable Long clientId) {
        return orderService.findClientHistory(clientId);
    }

    @GetMapping("open/client/{clientId}")
    public OrderDto openByClientId(@PathVariable Long clientId) {
        return orderService.getOpenByClient(clientId);
    }

    @GetMapping("open/driver/{driverId}")
    public OrderDto openByDriverId(@PathVariable Long driverId) {
        return orderService.getOpenByDriver(driverId);
    }

    @GetMapping("history/driver/{driverId}")
    public List<OrderDto> driverHistory(@PathVariable Long driverId) {
        return orderService.findDriverHistory(driverId);
    }

    @PostMapping("{id}/taken/{driverId}")
    public void setTaken(@PathVariable Long id, @PathVariable Long driverId) {
        orderService.setTaken(id, driverId);
    }

    @PostMapping("{id}/canceled")
    public void setCanceled(@PathVariable Long id) {
        orderService.setCanceled(id);
    }

    @PostMapping("{id}/closed")
    public void setClosed(@PathVariable Long id) {
        orderService.setClosed(id);
    }
}
