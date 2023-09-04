package org.tptacs.presentation.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ItemOrderDto {
	
	@JsonProperty("item")
    private ItemDto item;
	@JsonProperty("quantity")
    private Long quantity;
	
	@JsonCreator
	public ItemOrderDto(ItemDto item, Long quantity) {
		this.item = item;
		this.quantity = quantity;
	}
	
}
