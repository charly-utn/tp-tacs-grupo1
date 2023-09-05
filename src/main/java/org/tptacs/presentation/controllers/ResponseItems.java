package org.tptacs.presentation.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.tptacs.domain.entities.ItemOrder;
import org.tptacs.presentation.dto.ItemOrderDto;
import org.tptacs.presentation.responseModels.Response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ResponseItems extends Response{
	@JsonProperty("Items")
	private List<ItemOrderDto> items;

	@JsonCreator
	public ResponseItems(List<ItemOrder> items) {
		super();
		this.items = items.stream().map(ItemOrder::toDto).collect(Collectors.toList());
	}
	
	
}
