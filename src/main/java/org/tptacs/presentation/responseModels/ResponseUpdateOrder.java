package org.tptacs.presentation.responseModels;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class ResponseUpdateOrder extends ResponseGeneric{

	@Schema(description = "If the order was updated")
	@JsonProperty("updateOrder")
	private Boolean updateOrder;
	
	@JsonCreator
    public ResponseUpdateOrder(Boolean updateOrder) {
		super();
		this.updateOrder = updateOrder;
    }

}
