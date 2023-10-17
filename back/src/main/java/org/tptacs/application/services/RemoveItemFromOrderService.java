package org.tptacs.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tptacs.application.useCases.RemoveItemFromOrderUseCase;
import org.tptacs.infraestructure.repositories.OrderRepository;
import org.tptacs.infraestructure.repositories.ProductRepository;

@Service
public class RemoveItemFromOrderService implements RemoveItemFromOrderUseCase {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public void removeItemFromOrder(String orderId, String productId) {
        var order = this.orderRepository.findById(orderId);

        var product = productRepository.findById(productId);

        order.get().removeItem(product.get());

        orderRepository.save(order.get());		
	}

}
