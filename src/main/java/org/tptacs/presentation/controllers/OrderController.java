package org.tptacs.presentation.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tptacs.application.useCases.AddItemToOrderUC;
import org.tptacs.application.useCases.CreateOrderUC;
import org.tptacs.application.useCases.GetItemsFromOrderUC;
import org.tptacs.application.useCases.RemoveItemFromOrderUC;
import org.tptacs.application.useCases.UpdateOrderUC;
import org.tptacs.domain.enums.OrderStatus;
import org.tptacs.presentation.requestModels.ItemOrderRequest;
import org.tptacs.presentation.requestModels.OrderRequest;
import org.tptacs.presentation.responseModels.Response;
import org.tptacs.presentation.responseModels.ItemResponse;
import org.tptacs.presentation.responseModels.OrderResponse;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Orders")
@RequestMapping(value = "/api/orders", produces = "application/json", consumes = "application/json"  )
public class OrderController {

    private final CreateOrderUC createOrderUC;
    private final AddItemToOrderUC addItemToOrderUC;
    private final RemoveItemFromOrderUC removeItemFromOrderUC;
    private final GetItemsFromOrderUC getItemsFromOrderUC;
    private final UpdateOrderUC updateOrderUC;


    public OrderController(CreateOrderUC createOrderUC,
                           AddItemToOrderUC addItemToOrderUC,
                           GetItemsFromOrderUC getItemsFromOrderUC,
                           UpdateOrderUC updateOrderUC,
                           RemoveItemFromOrderUC removeItemFromOrderUC) {
        this.createOrderUC = createOrderUC;
        this.addItemToOrderUC = addItemToOrderUC;
        this.getItemsFromOrderUC = getItemsFromOrderUC;
        this.updateOrderUC = updateOrderUC;
        this.removeItemFromOrderUC = removeItemFromOrderUC;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) {
        String orderId = createOrderUC.createOrder(orderRequest);
		return ResponseEntity.ok().body(new OrderResponse(orderId));
    }

    @PostMapping("/{orderId}/items")
    public ResponseEntity<ItemResponse> createItem(@RequestBody ItemOrderRequest itemOrderRequest, @PathVariable("orderId") String orderID) {
        addItemToOrderUC.addItemToOrder(orderID, itemOrderRequest);
        
        return ResponseEntity.ok().body(new ItemResponse(itemOrderRequest.getId()));
    }

    @GetMapping("/{orderId}/items")
    public ResponseEntity<ResponseItems> getItems(@PathVariable("orderId") String orderId) {
        return ResponseEntity.ok().body(new ResponseItems(getItemsFromOrderUC.getItemsFromOrder(orderId)));
    }
    
	@PatchMapping("/{orderId}")
    public ResponseEntity<Response> updateOrder(@PathVariable("orderId") String orderId) {
		updateOrderUC.updateStatusOrder(orderId, 1L, OrderStatus.CLOSED);
		return ResponseEntity.ok().body(new Response());
    }
	
	@PatchMapping("/{orderId}/items/{itemId}")
    public ResponseEntity<Response> updateItemOrder(@RequestBody int quantity, @PathVariable("orderId") String orderId, @PathVariable("itemId") String itemId) {
		
		return ResponseEntity.ok().body(new Response());
    }


    @DeleteMapping("/{orderId}/items/{itemId}")
    public void removeItem(@PathVariable("orderId") String orderID, @PathVariable("itemId") String itemID) {
        removeItemFromOrderUC.removeItemFromOrder(orderID, itemID);
    }

}
