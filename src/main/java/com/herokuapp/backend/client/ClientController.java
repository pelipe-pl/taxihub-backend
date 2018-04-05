package com.herokuapp.backend.client;

import com.herokuapp.backend.order.OrderDto;
import com.herokuapp.backend.order.OrderService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("client")
@RestController
public class ClientController {

    private final OrderService orderService;

    public ClientController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("{id}")
    public List<OrderDto> myOrders(@PathVariable Long id) {
        return orderService.findByClientId(id);
    }

    @PostMapping("order")
    public void newOrder(@RequestBody OrderDto orderDto) {
        orderService.add(orderDto);
    }
}
