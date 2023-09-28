package org.tptacs.presentation.responseModels;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class OrdersResponse extends Response{


    @Schema(description = "idientifiers of orders")
    @JsonProperty("OrdersIds")
    private List<String> id;

    @JsonCreator
    public OrdersResponse(List<String> orderIds) {
        super();
        this.id = orderIds;
    }

}
