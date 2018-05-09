package com.herokuapp.backend.client;

import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public void add(@RequestBody ClientDto clientDto) throws ExecutionException, InterruptedException {
        clientService.add(clientDto);
    }

    @PutMapping("{id}")
    public void edit(@PathVariable Long id, @RequestBody ClientDto clientDto) {
        clientDto.setId(id);
        clientService.edit(clientDto);
    }

    @GetMapping("/profile/{id}")
    public ClientDto getById(@PathVariable Long id) {
        return clientService.getById(id);
    }
}
