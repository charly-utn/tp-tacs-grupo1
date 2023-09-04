package org.tptacs.application.useCases;

import org.springframework.stereotype.Service;
import org.tptacs.domain.entities.Order;
import org.tptacs.domain.enums.OrderStatus;
import org.tptacs.infraestructure.repositories.interfaces.IOrderRepository;

import jakarta.validation.ValidationException;

@Service
public class UpdateOrderUC {
    private final IOrderRepository orderRepository;

	public UpdateOrderUC(IOrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public void updateStatusOrder(String orderId, Long userId, OrderStatus status) {
		Order orderDB = orderRepository.get(orderId);
		if(orderDB.getUserId().equals(userId)) throw new ValidationException("El usuario no esta autorizado");
		orderDB.upateStatus(status);
		orderRepository.save(orderDB);
	}
	
}
