package org.tptacs.application.useCases;

import org.springframework.stereotype.Service;
import org.tptacs.domain.entities.Order;
import org.tptacs.domain.enums.OrderStatus;
import org.tptacs.domain.exceptions.AuthorizationException;
import org.tptacs.infraestructure.repositories.interfaces.IOrderRepository;

@Service
public class UpdateOrderUC {
    private final IOrderRepository orderRepository;

	public UpdateOrderUC(IOrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public void updateStatusOrder(String orderId, String userId, OrderStatus status) {
		Order orderDB = orderRepository.get(orderId);
		if(!orderDB.getUserId().equals(userId)) throw new AuthorizationException("El usuario no esta autorizado para editar este pedido");
		orderDB.upateStatus(status);
		orderRepository.update(orderDB);
	}
	
}
