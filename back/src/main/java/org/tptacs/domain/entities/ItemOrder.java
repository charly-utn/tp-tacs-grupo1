package org.tptacs.domain.entities;

import org.tptacs.presentation.dto.ItemOrderDto;

import jakarta.validation.ValidationException;
import lombok.Getter;

@Getter
public class ItemOrder {

    private Product product;
    private Long quantity;

    public ItemOrder(Product product, Long quantity) {
        this.product = product;
        this.quantity = quantity;
    }

	public void updateQuantity(long quantity) {
		if(quantity <= 0) throw new ValidationException("La cantidad debe ser mayor a 0");
		this.quantity = quantity;
	}
    
	public ItemOrderDto toDto() {
    	ItemOrderDto resItem = new ItemOrderDto(product.toDto(),quantity);
    	return resItem;
    }
}
