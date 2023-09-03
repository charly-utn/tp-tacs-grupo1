package org.tptacs.application.useCases;

import org.springframework.stereotype.Service;
import org.tptacs.domain.entities.Order;
import org.tptacs.domain.enums.OrderStatus;
import org.tptacs.infraestructure.repositories.interfaces.IOrderRepository;

@Service
public class UpdateOrderUC {
    private final IOrderRepository orderRepository;

	public UpdateOrderUC(IOrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public Boolean updateStatusOrder(String orderId, Long userId, OrderStatus status) {
		Order orderDB = orderRepository.get(orderId);
		if(orderDB != null && orderDB.getUserId().equals(userId) && !orderDB.getStatus().equals(status)) {
			orderDB.setStatus(status);
			orderRepository.save(orderDB);
			return true;
		}
		return false;
	}
	
}
