package org.tptacs.presentation.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

import java.util.UUID;

@Data
public class ItemOrderDto {
	private String id;
    private ItemDto item;
	private String userId;
	private Long total;
	private Long quantity;
	
	@JsonCreator
	public ItemOrderDto(String userId, ItemDto item, Long quantity, Long total) {
		this.userId = userId;
		this.id = UUID.randomUUID().toString();
		this.item = item;
		this.quantity = quantity;
		this.total = total;
	}
}
