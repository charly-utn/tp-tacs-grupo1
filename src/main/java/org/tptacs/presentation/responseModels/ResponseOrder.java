package org.tptacs.presentation.responseModels;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class ResponseOrder extends ResponseGeneric{

	
	@Schema(description = "idientifier of order")
	@JsonProperty("OrderId")
	private String id;

	@JsonCreator
    public ResponseOrder(String orderId) {
		super();
		this.id = orderId;
    }

}
