package com.herokuapp.backend.client;

import com.herokuapp.backend.order.OrderDto;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("client")
@RestController
public class ClientController {

    private final ClientService clientService;
    //private final ClientRepository clientRepository;

    public ClientController(ClientService clientService, ClientRepository clientRepository) {
        this.clientService = clientService;
        //this.clientRepository = clientRepository;
    }

    @GetMapping("{id}")
    public OrderDto myOrders(@PathVariable Long id) {
        return clientService.findAll(id);
    }

//odkomentować, jeśli będzie już order panel i wtedy usunąć powyższą metodę
//    @GetMapping("{id}")
//    public List<OrderDto> myOrders(@PathVariable Long id) {
//        return clientService.findAllById(id);
//    }

    @PostMapping("order")
    public OrderDto newOrder(@RequestBody OrderDto orderDto) {
        return clientService.newOrder(orderDto);
    }
}
