package com.herokuapp.backend.client;

import com.herokuapp.backend.order.OrderDto;
import com.herokuapp.backend.order.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository, OrderRepository orderRepository) {
        this.clientRepository = clientRepository;
    }

    private ClientEntity toEntity(ClientDto clientDto) {
        if (!clientRepository.existsByEmail(clientDto.getEmail())) {
            return new ClientEntity(
                    clientDto.getEmail(),
                    clientDto.getName(),
                    clientDto.getSurname());
        } else {
            return new ClientEntity(
                    clientDto.getId(),
                    clientDto.getEmail(),
                    clientDto.getName(),
                    clientDto.getSurname());
        }
    }

    private ClientDto toDto(ClientEntity clientEntity) {
        return new ClientDto(
                clientEntity.getId(),
                clientEntity.getEmail(),
                clientEntity.getName(),
                clientEntity.getSurname());
    }
}
