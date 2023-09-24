package org.tptacs.presentation.responseModels;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class AnalyticsResponse extends Response {
	
	
	@Schema(description = "Response internal code")
	@JsonProperty("quantity")
	private Long quantity;

	@JsonCreator
	public AnalyticsResponse(Long quantity) {
		this.quantity = quantity;
	}
		

}
