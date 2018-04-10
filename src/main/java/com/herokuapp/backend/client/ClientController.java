package com.herokuapp.backend.client;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public void add (@RequestBody ClientDto clientDto){
        clientService.add(clientDto);
    }

    @PutMapping("{id}")
    public void edit (@PathVariable Long id,@RequestBody ClientDto clientDto ) {
        clientDto.setId(id);
        clientService.edit(clientDto);
    }
}
