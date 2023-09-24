package org.tptacs.domain.entities;

import java.math.BigDecimal;

import org.tptacs.presentation.dto.ItemOrderDto;

import jakarta.validation.ValidationException;
import lombok.Getter;

@Getter
public class ItemOrder {
    private Item item;
    private Long quantity;

    public ItemOrder(Item item, Long quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return this.item.getPrice().multiply(new BigDecimal(this.quantity));
    }
    
    public ItemOrderDto toDto() {
    	ItemOrderDto resItem = new ItemOrderDto(item.toDto(),quantity);
    	return resItem;
    }

	public void updateQuantity(Long quantity) {
		if(quantity <= 0) throw new ValidationException("La cantidad debe ser mayor a 0");
		this.quantity = quantity;
	}
}