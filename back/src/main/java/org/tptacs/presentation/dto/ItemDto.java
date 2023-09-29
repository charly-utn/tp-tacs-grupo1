package org.tptacs.presentation.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemDto {
	@JsonProperty("idItem")
    private String id;
	@JsonProperty("name")
    private String name;
	@JsonProperty("price")
    private BigDecimal price;
    @JsonProperty("price")
    private String picture;
	
	@JsonCreator
	public ItemDto(String id, String name, BigDecimal price, String picture) {
		this.id = id;
		this.name = name;
		this.price = price;
        this.picture = picture;
	}
	
	

}
