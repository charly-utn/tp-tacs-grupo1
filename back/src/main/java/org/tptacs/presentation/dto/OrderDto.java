package org.tptacs.presentation.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderDto {
    @JsonProperty("orderId")
    private String orderId;
    @JsonProperty("userId")
    private String userId;
    @JsonProperty("status")
    private String status;
    @JsonProperty("hasItems")
    private boolean hasItems;

    @JsonCreator
    public OrderDto(String orderId, String userId, String status, boolean hasItems) {
        this.orderId = orderId;
        this.userId = userId;
        this.status = status;
        this.hasItems = hasItems;
    }
}