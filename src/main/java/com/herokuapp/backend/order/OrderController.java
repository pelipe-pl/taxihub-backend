package com.herokuapp.backend.order;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.herokuapp.backend.order.OrderStatus.OPEN;

@RestController
@RequestMapping("orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("{id}")
    public OrderDto one(@PathVariable Long id) {
        return orderService.findById(id);
    }

    @GetMapping("open")
    public List<OrderDto> allOpen() {
        return orderService.findAllByStatus(OPEN);
    }

    @PostMapping
    public void add(@RequestBody OrderDto orderDto) {
        orderService.add(orderDto);
    }

    @GetMapping("history/client/{clientId}")
    public List<OrderDto> clientHistory(@PathVariable Long clientId) {
        return orderService.findClientHistory(clientId);
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