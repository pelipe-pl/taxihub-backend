package com.herokuapp.backend.client;

import com.herokuapp.backend.order.OrderDto;
import com.herokuapp.backend.order.OrderRepository;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    //private final OrderRepository orderRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
        //this.orderRepository = orderRepository;
    }

    //tylko do testow, usunac jak będzie gotowy order panel
    public OrderDto findAll(Long id) {
        return new OrderDto(
                //TODO, for tests only
                12L,
                20L,
                "OPEN",
                "56.1254432, 16.1251521",
                "56.1554432, 16.1291521");
    }

    //tylko do testów, usunąć, gdy będzie gotowy order panel
    public OrderDto newOrder(OrderDto orderDto) {
        return new OrderDto(
                12L,
                20L,
                "OPEN",
                "56.1254432, 16.1251521",
                "56.1554432, 16.1291521");
    }

//    List<OrderDto> findAllById(Long id);
    //TODO - trzeba przeniesc powyższa zakomentowaną metode do repo order i odkomentować poniższa, gdy już będzie gotowy panel order
//    public List<OrderDto> findAllById(Long clientId){
//        return orderRepository.findAllById(clientId).stream()
//                .map(orderDto -> new OrderDto()).collect(Collectors.toList());
//    }

    /* ta metoda będzie zapisywać zamówienie, odkomentować jak będzie panel order
    odkomentować też orderRepository w polu i w konstrutorze

    public void newOrder (OrderDto orderDto){
        orderRepository.save(orderDto.toEntity(orderDto));
    }
    */

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
