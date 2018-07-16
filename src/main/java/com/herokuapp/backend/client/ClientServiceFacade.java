package com.herokuapp.backend.client;


import org.springframework.stereotype.Service;

@Service
public class ClientServiceFacade {

    private final ClientRepository clientRepository;

    public ClientServiceFacade(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientEntity getById(Long id) {
        return clientRepository.getById(id);
    }

    public Boolean existsById(Long id) {
        return clientRepository.existsById(id);
    }

    public Long getIdByEmail(String email) {
        if (clientRepository.existsByEmail(email)) return clientRepository.findByEmail(email).getId();
        else throw new IllegalArgumentException("There is no client with this e-mail.");
    }
}
