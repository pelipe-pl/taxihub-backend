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

    public Boolean existsByEmail(String email) {
        return clientRepository.existsByEmail(email.toLowerCase());
    }

    public Long getIdByEmail(String email) {
        if (clientRepository.existsByEmail(email)) return clientRepository.findByEmail(email).getId();
        else throw new IllegalArgumentException("There is no client with this e-mail.");
    }

    public ClientEntity getByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    public void setPasswordResetToken(String email, String token) {
        ClientEntity clientEntity = clientRepository.findByEmail(email);
        clientEntity.setPasswordResetToken(token);
        clientRepository.save(clientEntity);
    }

    public String getEmailByPasswordResetToken(String token) {
        return clientRepository.getByPasswordResetToken(token).getEmail();
    }
}
