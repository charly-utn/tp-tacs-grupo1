package org.tptacs.application.useCases;

import org.springframework.stereotype.Service;
import org.tptacs.domain.entities.Order;
import org.tptacs.infraestructure.repositories.interfaces.IOrderRepository;

@Service
public class UpdateItemOrderUC {

    private final IOrderRepository orderRepository;

	public UpdateItemOrderUC(IOrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
    
	public void updateItemOrder(String orderId, String itemId, int quantity) {
		Order order = orderRepository.get(orderId);
		//order.getItems().stream().filter(ItemOrder::is)
	}
	
	
		
    

}
