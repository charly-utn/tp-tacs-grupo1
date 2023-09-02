package org.tptacs.presentation.controllers;

import org.springframework.web.bind.annotation.*;
import org.tptacs.application.useCases.AddItemToOrderUC;
import org.tptacs.presentation.requestModels.ItemOrderRequest;

@RestController
@RequestMapping("/api/order/{orderId}/items")
public class ItemController {

    private final AddItemToOrderUC addItemToOrderUC;

    public ItemController(AddItemToOrderUC addItemToOrderUC) {
        this.addItemToOrderUC = addItemToOrderUC;
    }

    @PostMapping
    public void createItem(@RequestBody ItemOrderRequest itemOrderRequest, @PathVariable("orderId") String orderID) {
        addItemToOrderUC.AddItemToOrder(orderID, itemOrderRequest);
    }
}
