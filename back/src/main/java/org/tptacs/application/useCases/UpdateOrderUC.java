package org.tptacs.application.useCases;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.tptacs.domain.entities.Order;
import org.tptacs.domain.entities.User;
import org.tptacs.domain.enums.OrderStatus;
import org.tptacs.infraestructure.repositories.interfaces.IOrderRepository;
import org.tptacs.infraestructure.repositories.interfaces.IUserRepository;

import jakarta.validation.ValidationException;

@Service
public class UpdateOrderUC {
    private final IOrderRepository orderRepository;
    private final IUserRepository userRepository;

	public UpdateOrderUC(IOrderRepository orderRepository, IUserRepository userRepository) {
		this.orderRepository = orderRepository;
		this.userRepository = userRepository;
	}

	public void updateStatusOrder(String orderId, String userId, OrderStatus status) {
		Order orderDB = orderRepository.get(orderId);
		if(!orderDB.getUserId().equals(userId)) throw new ValidationException("El usuario no esta autorizado");
		orderDB.upateStatus(status);
		orderRepository.update(orderDB);
	}
	
	public Order updateOrderForShared(String orderId, String userName){
		Order orderDB = orderRepository.get(orderId);
		Optional<User> userInvited = userRepository.findByUsername(userName);
		if(userInvited.isEmpty()) throw new ValidationException("El usuario no existe");
		userInvited.get().addOrderShared(orderDB.getId());
		orderDB.addUserInvited(userInvited.get().getUsername());
		orderRepository.update(orderDB);
		userRepository.update(userInvited.get());
		return orderDB;
	}
}
