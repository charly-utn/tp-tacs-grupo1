package org.tptacs.application.useCases;

import java.util.List;

import org.tptacs.domain.entities.Order;

public interface GetOrdersFromUserUseCase {
    
	public List<Order> getOrdersFromUser(String userId);
	public List<Order> getOrdersFromUserInvited(String userName);
}
