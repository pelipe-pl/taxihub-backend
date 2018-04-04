package com.herokuapp.backend.client;

import com.herokuapp.backend.order.OrderDto;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("client")
@RestController
public class ClientController {

    private final ClientService clientService;
    private final ClientRepository clientRepository;

    public ClientController(ClientService clientService, ClientRepository clientRepository) {
        this.clientService = clientService;
        this.clientRepository = clientRepository;
    }

    @GetMapping("")
    public OrderDto findAll() {
        return new OrderDto(
                12L,
                20L,
                "OPEN",
                "56.1254432, 16.1251521",
                "56.1554432, 16.1291521");
    }

    @PostMapping("neworder")
    public OrderDto newOrder(@RequestBody OrderDto orderDto) {
        //TODO
        //tu bedzie trzeba zmienic na takie, jak przyjdą w zrobionym orderze
        return new OrderDto(
                12L,
                20L,
                "OPEN",
                "56.1254432, 16.1251521",
                "56.1554432, 16.1291521");
        //LocalDateTime.now());
    }
}
