package com.herokuapp.backend.client;

import com.herokuapp.backend.auth.FirebaseRegistrationService;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final FirebaseRegistrationService firebaseRegistrationService;

    public ClientService(ClientRepository clientRepository, FirebaseRegistrationService firebaseRegistrationService) {
        this.clientRepository = clientRepository;
        this.firebaseRegistrationService = firebaseRegistrationService;
    }

    public void add(ClientDto clientDto) {
        clientRepository.save(toEntity(clientDto));
        firebaseRegistrationService.register(clientDto.getEmail(), clientDto.getPassword());
    }

    public void edit(ClientDto clientDto) {
        clientRepository.save(toEntity(clientDto));
    }

    private ClientEntity toEntity(ClientDto clientDto) {
        return new ClientEntity(
                clientDto.getId(),
                clientDto.getEmail(),
                clientDto.getName(),
                clientDto.getSurname());
    }

    private ClientDto toDto(ClientEntity clientEntity) {
        return new ClientDto(
                clientEntity.getId(),
                clientEntity.getEmail(),
                clientEntity.getName(),
                clientEntity.getSurname());
    }
}
