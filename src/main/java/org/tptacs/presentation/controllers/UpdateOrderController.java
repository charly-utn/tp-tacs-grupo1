package org.tptacs.presentation.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tptacs.application.useCases.UpdateOrderUC;
import org.tptacs.domain.enums.OrderStatus;
import org.tptacs.presentation.responseModels.ResponseUpdateOrder;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Orders")
@RequestMapping(value = "/api/orders", produces = "application/json")
public class UpdateOrderController {

    private final UpdateOrderUC updateOrderUC;

    public UpdateOrderController(UpdateOrderUC updateOrderUC) {
		super();
		this.updateOrderUC = updateOrderUC;
	}

	@PatchMapping("{orderId}/close/{userId}")
    public ResponseEntity<ResponseUpdateOrder> updateOrder(@PathVariable("orderId") String orderId, @PathVariable("userId") Long userId) {
		return ResponseEntity.ok().body(new ResponseUpdateOrder(updateOrderUC.updateStatusOrder(orderId, userId, OrderStatus.CLOSED)));
    }
}
