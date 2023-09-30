package org.tptacs.presentation.responseModels;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class OrderResponse extends Response{

	
	@Schema(description = "idientifier of order")
	@JsonProperty("orderId")
	private String id;

	@JsonCreator
    public OrderResponse(String orderId) {
		super();
		this.id = orderId;
    }

}
