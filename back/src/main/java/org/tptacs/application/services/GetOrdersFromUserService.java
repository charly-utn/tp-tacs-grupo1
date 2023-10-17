package org.tptacs.application.services;

import java.util.List;

import org.tptacs.application.useCases.GetOrdersFromUserUseCase;
import org.tptacs.domain.entities.Order;

public class GetOrdersFromUserService implements GetOrdersFromUserUseCase {

	@Override
	public List<Order> getOrdersFromUser(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getOrdersFromUserInvited(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

}
