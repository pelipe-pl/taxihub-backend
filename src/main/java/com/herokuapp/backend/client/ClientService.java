package com.herokuapp.backend.client;

import com.herokuapp.backend.auth.FirebaseRegistrationService;
import com.herokuapp.backend.auth.UserRegistrationChecker;
import com.herokuapp.backend.email.EmailService;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final FirebaseRegistrationService firebaseRegistrationService;
    private final EmailService emailService;
    private final UserRegistrationChecker userRegistrationChecker;

    public ClientService(ClientRepository clientRepository, FirebaseRegistrationService firebaseRegistrationService, EmailService emailService, UserRegistrationChecker userRegistrationChecker) {
        this.clientRepository = clientRepository;
        this.firebaseRegistrationService = firebaseRegistrationService;
        this.emailService = emailService;
        this.userRegistrationChecker = userRegistrationChecker;
    }

    public void add(ClientDto clientDto) throws ExecutionException, InterruptedException {
        if (!userRegistrationChecker.emailExists(clientDto.getEmail())) {
            ClientEntity clientEntity = toEntity(clientDto);
            clientRepository.save(clientEntity);
            firebaseRegistrationService.register(clientDto.getEmail(), clientDto.getPassword());
            emailService.sendWelcomeEmail(clientEntity);
        }
    }

    public ClientDto getById(Long id) {
        if (clientRepository.existsById(id)) {
            return toDto(clientRepository.getById(id));
        } else throw new IllegalArgumentException("There is no client with this id");
    }

    public void update(ClientDto clientDto) {
        if (clientRepository.existsById(clientDto.getId())) {
            if (clientDto.getName() != null && clientDto.getSurname() != null) {
                ClientEntity entity = clientRepository.getById(clientDto.getId());
                entity.setName(clientDto.getName());
                entity.setSurname(clientDto.getSurname());
                clientRepository.save(entity);
            } else throw new IllegalArgumentException("The name and surname are required!");
        } else throw new IllegalArgumentException("There is no client with this id!");
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
