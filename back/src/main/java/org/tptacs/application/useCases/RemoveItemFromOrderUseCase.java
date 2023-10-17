package org.tptacs.application.useCases;

public interface RemoveItemFromOrderUseCase {
	
    public void removeItemFromOrder(String orderId, String itemId);
}
