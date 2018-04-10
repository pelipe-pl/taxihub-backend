package com.herokuapp.backend.client;

import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void add(ClientDto clientDto) {
        clientRepository.save(toEntity(clientDto));
    }

    public void edit(ClientDto clientDto) {
        clientRepository.save(toEntity(clientDto));
    }

    private ClientEntity toEntity(ClientDto clientDto) {
        return new ClientEntity(
                clientDto.getId(),
                clientDto.getEmail(),
                clientDto.getName(),
                clientDto.getSurname(),
                clientDto.getPassword());
    }

    private ClientDto toDto(ClientEntity clientEntity) {
        return new ClientDto(
                clientEntity.getId(),
                clientEntity.getEmail(),
                clientEntity.getName(),
                clientEntity.getSurname(),
                clientEntity.getPassword());
    }
}
