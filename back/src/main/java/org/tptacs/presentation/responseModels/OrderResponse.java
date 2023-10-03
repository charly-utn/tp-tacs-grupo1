package org.tptacs.presentation.responseModels;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public class OrderResponse extends Response{

	
	@Schema(description = "idientifier of order")
	@JsonProperty("orderId")
	private String id;

	@JsonCreator
    public OrderResponse(String orderId, String code, String message) {
		super(code, message, LocalDateTime.now());
		this.id = orderId;
    }

}
