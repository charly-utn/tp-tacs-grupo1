package org.tptacs.presentation.requestModels;

import lombok.Getter;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
public class OrderRequest {
	@JsonProperty("userId")
    private Long userId;
	@JsonProperty("items")
    private List<ItemOrderRequest> items;
	
	@JsonCreator
    public OrderRequest(
            @JsonProperty("userId") Long userId,
            @JsonProperty("items") List<ItemOrderRequest> items) {
        this.userId = userId;
        this.items = items;
    }
    
	
    
}
