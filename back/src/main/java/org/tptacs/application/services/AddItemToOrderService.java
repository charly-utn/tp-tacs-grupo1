package org.tptacs.application.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tptacs.application.useCases.AddItemToOrderUseCase;
import org.tptacs.domain.entities.ItemOrder;
import org.tptacs.domain.entities.Order;
import org.tptacs.domain.entities.Product;
import org.tptacs.domain.exceptions.NotFoundException;
import org.tptacs.infraestructure.repositories.OrderRepository;
import org.tptacs.infraestructure.repositories.ProductRepository;
import org.tptacs.presentation.requestModels.ItemOrderRequest;

@Service
public class AddItemToOrderService implements AddItemToOrderUseCase{

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public void addItemToOrder(String orderId, ItemOrderRequest orderRequest) {
		Optional<Order> order = orderRepository.findById(orderId);
		if(order.isEmpty()) throw new NotFoundException(orderId, "Order");
		var item= order.get().getItems().stream().filter( i -> i.getProduct().getId().equals(orderRequest.getId())).findFirst();
        item.ifPresentOrElse(
                existingItem -> existingItem.updateQuantity(existingItem.getQuantity() + orderRequest.getQuantity()),
                () -> {
                    Optional<Product> product = productRepository.findById(orderRequest.getId());
            		if(product.isEmpty()) throw new NotFoundException(orderRequest.getId(), "Product");
                    ItemOrder itemOrder = new ItemOrder(product.get(), orderRequest.getQuantity());
                    order.get().addItem(itemOrder);
                }
            );
        orderRepository.save(order.get());
	}

}
