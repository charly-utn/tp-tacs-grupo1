package org.tptacs.application.useCases;

import org.springframework.stereotype.Service;
import org.tptacs.domain.entities.ItemOrder;

import jakarta.validation.ValidationException;

@Service
public class UpdateItemOrderUC {

    private final GetItemsFromOrderUC getItemsFromOrderUC;

	public UpdateItemOrderUC(GetItemsFromOrderUC getItemsFromOrderUC) {
		this.getItemsFromOrderUC = getItemsFromOrderUC;
	}
    
	public void updateItemOrder(String orderId, String itemId, Long quantity) {
		ItemOrder item = getItemsFromOrderUC.getItemFromOrder(orderId, itemId);
		item.updateQuantity(quantity);
	}
	
	    

}
