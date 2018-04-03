package com.herokuapp.backend.client;

import com.herokuapp.backend.order.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;

    public ClientService(ClientRepository clientRepository, OrderRepository orderRepository) {
        this.clientRepository = clientRepository;
        this.orderRepository = orderRepository;
    }

    private void addNewOrder(){

    }


}
