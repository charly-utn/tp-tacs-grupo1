package org.tptacs.application.useCases;

import org.springframework.stereotype.Service;
import org.tptacs.infraestructure.repositories.interfaces.IOrderRepository;
import org.tptacs.infraestructure.repositories.interfaces.IUserRepository;

@Service
public class GetAnalyticsUC {
	
    private final IOrderRepository orderRepository;
    private final IUserRepository userRepository;

	public GetAnalyticsUC(IOrderRepository orderRepository, IUserRepository userRepository) {
		this.orderRepository = orderRepository;
		this.userRepository = userRepository;
	}
	
	public Long countOrders() {
		return orderRepository.count();
	}
	
	public Long countUsersUnique() {
		return userRepository.countUserUnique();
	}

	
}
