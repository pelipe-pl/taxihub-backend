package com.herokuapp.backend.client;

import com.herokuapp.backend.order.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    //private final OrderRepository orderRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
        //this.orderRepository = orderRepository;
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
