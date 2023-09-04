package org.tptacs.application.useCases;

import org.springframework.stereotype.Service;
import org.tptacs.infraestructure.repositories.interfaces.IOrderRepository;

@Service
public class GetAnalyticsUC {
	
    private final IOrderRepository orderRepository;

	public GetAnalyticsUC(IOrderRepository orderRepository) {
		super();
		this.orderRepository = orderRepository;
	}
	
	public Long countOrders() {
		return orderRepository.count();
	}

	
}
