package org.tptacs.presentation.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tptacs.application.useCases.AddItemToOrderUC;
import org.tptacs.application.useCases.CreateOrderUC;
import org.tptacs.application.useCases.GetItemsFromOrderUC;
import org.tptacs.domain.entities.ItemOrder;
import org.tptacs.presentation.requestModels.ItemOrderRequest;
import org.tptacs.presentation.requestModels.OrderRequest;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final CreateOrderUC createOrderUC;
    private final AddItemToOrderUC addItemToOrderUC;
    private final GetItemsFromOrderUC getItemsFromOrderUC;

    public OrderController(CreateOrderUC createOrderUC,
                           AddItemToOrderUC addItemToOrderUC,
                           GetItemsFromOrderUC getItemsFromOrderUC) {
        this.createOrderUC = createOrderUC;
        this.addItemToOrderUC = addItemToOrderUC;
        this.getItemsFromOrderUC = getItemsFromOrderUC;
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest orderRequest) {
        return ResponseEntity.ok(createOrderUC.createOrder(orderRequest));
    }

    @PostMapping("/{orderId}/items")
    public void createItem(@RequestBody ItemOrderRequest itemOrderRequest, @PathVariable("orderId") String orderID) {
        addItemToOrderUC.addItemToOrder(orderID, itemOrderRequest);
    }

    @GetMapping("/{orderId}/items")
    public @ResponseBody List<ItemOrder> getItems(@PathVariable("orderId") String orderId) {
        return getItemsFromOrderUC.getItemsFromOrder(orderId);
    }
}
