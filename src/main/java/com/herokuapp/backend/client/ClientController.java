package com.herokuapp.backend.client;

import com.herokuapp.backend.order.OrderDto;
import org.springframework.web.bind.annotation.*;

@RequestMapping("client")
@RestController
public class ClientController {

//    private final ClientService clientService;
//
//    public ClientController(ClientService clientService) {
//        this.clientService = clientService;
//    }

    @PostMapping("neworder")
    public void newOrder (@RequestBody OrderDto orderDto){
        //TODO
        //metoda z order do zapisu
    }

}
