package org.tptacs.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tptacs.application.useCases.GetAnalyticsUseCase;
import org.tptacs.infraestructure.repositories.OrderRepository;
import org.tptacs.infraestructure.repositories.UserRepository;

@Service
public class GetAnalyticsService implements GetAnalyticsUseCase {
	
	@Autowired
    private OrderRepository orderRepository;
	@Autowired
    private UserRepository userRepository;

	public Long countOrders() {
		return orderRepository.count();
	}
	
	public Long countUsersUnique() {
		return userRepository.count();
	}
}
