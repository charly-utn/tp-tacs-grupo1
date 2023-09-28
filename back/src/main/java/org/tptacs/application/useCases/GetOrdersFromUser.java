package org.tptacs.application.useCases;

import org.springframework.stereotype.Service;
import org.tptacs.domain.entities.Order;
import org.tptacs.infraestructure.repositories.interfaces.IOrderRepository;

import java.util.List;

@Service
public class GetOrdersFromUser {
    private final IOrderRepository orderRepository;

    public GetOrdersFromUser(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getOrdersFromUser(String userId) {
        return orderRepository.getOrdersFromUser(userId);
    }

}
