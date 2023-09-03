package org.tptacs.domain.entities;

import org.tptacs.presentation.dto.ItemOrderDto;

import lombok.Getter;

@Getter
public class ItemOrder {
    private Item item;
    private Long quantity;

    public ItemOrder(Item item, Long quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public Double getPrice() {
        return this.item.getPrice() * this.quantity;
    }
    
    public ItemOrderDto toDto() {
    	ItemOrderDto resItem = new ItemOrderDto(item.toDto(),quantity);
    	return resItem;
    }
}
