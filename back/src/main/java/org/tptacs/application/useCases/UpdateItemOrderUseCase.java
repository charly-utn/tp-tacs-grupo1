package org.tptacs.application.useCases;

public interface UpdateItemOrderUseCase {

	public void updateItemOrder(String orderId, String itemId, Long quantity);
}
