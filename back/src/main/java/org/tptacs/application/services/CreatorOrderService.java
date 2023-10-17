package org.tptacs.application.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tptacs.application.useCases.CreatorOrderUseCase;
import org.tptacs.domain.entities.ItemOrder;
import org.tptacs.domain.entities.Order;
import org.tptacs.domain.entities.Product;
import org.tptacs.domain.enums.OrderStatus;
import org.tptacs.domain.exceptions.NotFoundException;
import org.tptacs.infraestructure.repositories.OrderRepository;
import org.tptacs.infraestructure.repositories.ProductRepository;
import org.tptacs.presentation.requestModels.OrderRequest;

@Service
public class CreatorOrderService implements CreatorOrderUseCase {
	
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private ProductRepository productRepository;

	@Override
	public Order createOrder(OrderRequest orderRequest) {
		List<ItemOrder> items = orderRequest.getItems().stream().map( it ->
			{
				Optional<Product> product = productRepository.findById(it.getId());
				if(product.isEmpty()) throw new NotFoundException(it.getId(), "Product");
				return new ItemOrder(product.get(), it.getQuantity());
			}).collect(Collectors.toList());
		Order ped = new Order(UUID.randomUUID().toString(), orderRequest.getUserId(), orderRequest.getName(), items, OrderStatus.NEW);
		orderRepository.save(ped);
		return ped;
	}
}

