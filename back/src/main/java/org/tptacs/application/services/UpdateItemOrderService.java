package org.tptacs.application.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tptacs.application.useCases.UpdateItemOrderUseCase;
import org.tptacs.domain.entities.ItemOrder;
import org.tptacs.domain.entities.Order;
import org.tptacs.domain.exceptions.NotFoundException;
import org.tptacs.infraestructure.repositories.OrderRepository;

@Service
public class UpdateItemOrderService implements UpdateItemOrderUseCase {

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public void updateItemOrder(String orderId, String productId, Long quantity) {
		Optional<Order> orderOp = orderRepository.findById(orderId);
		if(orderOp.isEmpty()) throw new NotFoundException(orderId, "Orders");
		ItemOrder item = orderOp.get().getItemFromOrder(productId);
		item.updateQuantity(quantity);
		orderOp.get().updateItemOrder(item);
		orderRepository.save(orderOp.get());
	}

}
