package org.tptacs.presentation.responseModels;

import java.util.List;
import java.util.stream.Collectors;

import org.tptacs.domain.entities.ItemOrderOld;
import org.tptacs.presentation.dto.ItemOrderDto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ItemsResponseOld extends Response{
	@JsonProperty("Items")
	private List<ItemOrderDto> items;

	@JsonCreator
	public ItemsResponseOld(List<ItemOrderOld> items) {
		super();
		this.items = items.stream().map(ItemOrderOld::toDto).collect(Collectors.toList());
	}
	
	
}
