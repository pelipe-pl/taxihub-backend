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


    @GetMapping("{id}")
    public OrderDto one(@PathVariable Long id) {
        return orderService.findById(id);
    }

    @GetMapping("")
    public List<OrderDto> all() {
        return orderService.findAll();
    }

    @PostMapping()
    public void add(@RequestBody OrderDto orderDto){
        orderService.add(orderDto);
    }




}
