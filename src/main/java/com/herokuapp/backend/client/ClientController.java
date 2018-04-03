package com.herokuapp.backend.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("client")
@RestController
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("neworder")
    public void newOrder

}

/*

@RequestMapping("players")
@RestController
public class PlayerController {
    private final PlayerService service;

    public PlayerController(PlayerService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<PlayerDto> all() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public PlayerDto one(@PathVariable Integer id) {
        Optional<PlayerDto> byId = service.findById(id);
        return byId.orElseThrow(() -> new PlayerNotFoundException(id));
    }

    @PostMapping("")
    public void save(@RequestBody PlayerDto player) {
        service.savePlayer(player);
    }

    @PutMapping("{id}")
    public void edit (@PathVariable Integer id,@RequestBody PlayerDto player ) {
        player.setId(id);
        service.editPlayer(player);
    }

    @DeleteMapping("{id}")
    public void delete (@PathVariable Integer id){
        service.deletePlayer(id);
    }
}
 */