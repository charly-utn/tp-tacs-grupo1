package org.tptacs.presentation.responseModels;


import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseItem extends ResponseGeneric{

	@JsonProperty("ItemId")
	private String idItem;
	
	public ResponseItem(String idItem) {
		super();
		this.idItem = idItem;
	}

}
