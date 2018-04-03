package com.herokuapp.backend.order;

import com.herokuapp.backend.order.OrderDto;
import com.herokuapp.backend.order.OrderEntity;
import com.herokuapp.backend.order.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

//    private final ClientRepository clientRepository;
//    private final OrderRepository orderRepository;
//
//    public OrderService(ClientRepository clientRepository, OrderRepository orderRepository) {
//        this.clientRepository = clientRepository;
//        this.orderRepository = orderRepository;
//    }
//
//    public void newOrder (OrderDto orderDto){
//        orderRepository.save(toEntity(orderDto));
//    }
//
//    public OrderEntity toEntity(OrderDto orderDto){
//        return new OrderEntity(
                //TODO
//        );

//    }


}

/*
    private PlayerEntity toEntity(PlayerDto playerDto) {

        if (!teamRepository.existsByName(playerDto.getTeamName())) {
            TeamEntity teamEntity = new TeamEntity(playerDto.getTeamName());
            teamRepository.save(teamEntity);

            return new PlayerEntity(
                    playerDto.getId(),
                    playerDto.getName(),
                    playerDto.getSurname(),
                    teamEntity);

        } else {
            return new PlayerEntity(
                    playerDto.getId(),
                    playerDto.getName(),
                    playerDto.getSurname(),
                    teamRepository.findByName(playerDto.getTeamName()));
        }
 */
