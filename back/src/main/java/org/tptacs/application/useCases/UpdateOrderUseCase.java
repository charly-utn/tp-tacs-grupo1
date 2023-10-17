package org.tptacs.application.useCases;

import org.tptacs.domain.entities.Order;
import org.tptacs.domain.enums.OrderStatus;

public interface UpdateOrderUseCase {

	public void updateStatusOrder(String orderId, String userId, OrderStatus status);
	public Order updateOrderForShared(String orderId, String userName);
}
