package org.tptacs.domain.entities;

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
}
