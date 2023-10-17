package org.tptacs.application.useCases;

import java.util.List;

import org.tptacs.domain.entities.ItemOrder;

public interface GetItemFromOrderUseCase {

    public List<ItemOrder> getItemsFromOrder(String orderId);
    
}
