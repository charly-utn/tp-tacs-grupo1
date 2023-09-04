package org.tptacs.domain.entities;

import java.math.BigDecimal;

import org.tptacs.presentation.dto.ItemDto;

import lombok.Getter;

@Getter
public class Item {
    private String id;
    private String name;
    private BigDecimal price;

    public Item(String id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

	public ItemDto toDto() {
		ItemDto itemDto = new ItemDto(id,name,price);
		return itemDto;
	}
}
