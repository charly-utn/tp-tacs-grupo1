package org.tptacs.presentation.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tptacs.application.useCases.CreateOrderUC;
import org.tptacs.presentation.requestModels.OrderRequest;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final CreateOrderUC createOrderUC;

    public OrderController(CreateOrderUC createOrderUC) {
        this.createOrderUC = createOrderUC;
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest orderRequest) {
        return ResponseEntity.ok(createOrderUC.createOrder(orderRequest));
    }
}
