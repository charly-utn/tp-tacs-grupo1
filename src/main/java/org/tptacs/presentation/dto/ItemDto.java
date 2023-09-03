package org.tptacs.presentation.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemDto {
	@JsonProperty("idItem")
    private String id;
	@JsonProperty("name")
    private String name;
	@JsonProperty("price")
    private Double price;
	
	@JsonCreator
	public ItemDto(String id, String name, Double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	

}
