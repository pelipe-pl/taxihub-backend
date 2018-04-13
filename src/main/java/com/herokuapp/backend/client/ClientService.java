package com.herokuapp.backend.client;

import com.herokuapp.backend.auth.FirebaseRegistrationService;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final FirebaseRegistrationService firebaseRegistrationService;

    public ClientService(ClientRepository clientRepository, FirebaseRegistrationService firebaseRegistrationService) {
        this.clientRepository = clientRepository;
        this.firebaseRegistrationService = firebaseRegistrationService;
    }

    public void add(ClientDto clientDto) throws ExecutionException, InterruptedException {
        if (clientRepository.existsByEmail(clientDto.getEmail())) {
            clientRepository.save(toEntity(clientDto));
            firebaseRegistrationService.register(clientDto.getEmail(), clientDto.getPassword());
        } else throw new IllegalArgumentException("Client with this e-mail already exists");
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
