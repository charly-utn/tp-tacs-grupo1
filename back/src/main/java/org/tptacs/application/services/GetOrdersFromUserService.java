package org.tptacs.application.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tptacs.application.useCases.GetOrdersFromUserUseCase;
import org.tptacs.domain.entities.Order;
import org.tptacs.domain.entities.User;
import org.tptacs.domain.exceptions.NotFoundException;
import org.tptacs.infraestructure.repositories.OrderRepository;
import org.tptacs.infraestructure.repositories.UserRepository;

@Service
public class GetOrdersFromUserService implements GetOrdersFromUserUseCase {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<Order> getOrdersFromUser(String userId) {
		List<Order> order = orderRepository.findByUserId(userId);
		return order;
	}

	@Override
	public List<Order> getOrdersFromUserInvited(String userId) {
		Optional<User> userIdOptional = userRepository.findById(userId);
		if(userIdOptional.isEmpty()) throw new NotFoundException(userId, "Users");
		return userIdOptional
				.get()
				.getOrderShared()
				.stream()
				.map(id -> orderRepository.findById(id).get())
					.collect(Collectors.toList());
	}
}
