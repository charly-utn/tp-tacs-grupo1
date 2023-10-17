package org.tptacs.application.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tptacs.application.useCases.GetItemFromOrderUseCase;
import org.tptacs.domain.entities.ItemOrder;
import org.tptacs.domain.entities.Order;
import org.tptacs.domain.exceptions.NotFoundException;
import org.tptacs.infraestructure.repositories.OrderRepository;

@Service
public class GetItemFromOrderService implements GetItemFromOrderUseCase {

	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public List<ItemOrder> getItemsFromOrder(String orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if(order.isEmpty()) throw new NotFoundException(orderId, "Order");
        return order.get().getItems();
	}

}
