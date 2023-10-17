package org.tptacs.application.useCases;

import org.tptacs.presentation.requestModels.ItemOrderRequest;

public interface AddItemToOrderUseCase {

    public void addItemToOrder(String orderId, ItemOrderRequest orderRequest);
    
}
