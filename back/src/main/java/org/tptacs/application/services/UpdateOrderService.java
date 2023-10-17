package org.tptacs.application.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tptacs.application.useCases.UpdateOrderUseCase;
import org.tptacs.domain.entities.Order;
import org.tptacs.domain.entities.User;
import org.tptacs.domain.enums.OrderStatus;
import org.tptacs.domain.exceptions.AuthorizationException;
import org.tptacs.domain.exceptions.NotFoundException;
import org.tptacs.infraestructure.repositories.OrderRepository;
import org.tptacs.infraestructure.repositories.UserRepository;

@Service
public class UpdateOrderService implements UpdateOrderUseCase{

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void updateStatusOrder(String orderId, String userId, OrderStatus status) {
		Optional<Order> orderOp = orderRepository.findById(orderId);
		if(orderOp.isEmpty()) throw new NotFoundException(orderId, "Orders");
		if(!orderOp.get().getUserId().equals(userId)) throw new AuthorizationException("El usuario no esta autorizado para editar este pedido");
		orderOp.get().updateStatus(status);
		orderRepository.save(orderOp.get());
		
	}

	@Override
	public Order updateOrderForShared(String orderId, String userId) {
		Optional<Order> orderDB = orderRepository.findById(orderId);
		if(orderDB.isEmpty()) throw new NotFoundException(orderId, "Orders"); 
		Optional<User> userInvited = userRepository.findById(userId);
		if(userInvited.isEmpty()) throw new NotFoundException(userId, "Users");
		userInvited.get().addOrderShared(orderDB.get().getId());
		orderDB.get().addUserInvited(userInvited.get().getUsername());
		orderRepository.save(orderDB.get());
		userRepository.save(userInvited.get());
		return orderDB.get();
	}

}
