package org.tptacs.presentation.requestModels;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class ItemOrderRequest {
	@JsonProperty("id")
    private String id;
	@JsonProperty("quantity")
    private Long quantity;
	
    @JsonCreator
    public ItemOrderRequest(
            @JsonProperty("id") String id,
            @JsonProperty("quantity") Long quantity) {
        this.id = id;
        this.quantity = quantity;
    }
	
}
