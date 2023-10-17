package org.tptacs.presentation.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tptacs.application.useCases.AddItemToOrderUseCase;
import org.tptacs.application.useCases.CreatorOrderUseCase;
import org.tptacs.application.useCases.GetItemFromOrderUseCase;
import org.tptacs.domain.entities.Order;
import org.tptacs.domain.entities.OrderOld;
import org.tptacs.domain.enums.OrderStatus;
import org.tptacs.presentation.dto.OrderDto;
import org.tptacs.presentation.requestModels.ItemOrderRequest;
import org.tptacs.presentation.requestModels.OrderRequest;
import org.tptacs.presentation.requestModels.UpdateQuantity;
import org.tptacs.presentation.responseModels.ItemResponse;
import org.tptacs.presentation.responseModels.ItemsResponse;
import org.tptacs.presentation.responseModels.ItemsResponseOld;
import org.tptacs.presentation.responseModels.OrderResponse;
import org.tptacs.presentation.responseModels.OrderResponseOld;
import org.tptacs.presentation.responseModels.OrdersResponse;
import org.tptacs.presentation.responseModels.Response;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Orders")
@RequestMapping(value = "/v2/api/orders")
public class OrderController extends BaseController {

    @Autowired
    private CreatorOrderUseCase creatorOrderUseCase;
    
    @Autowired
    private AddItemToOrderUseCase addItemToOrderUseCase;
    
    @Autowired
    private GetItemFromOrderUseCase getItemFromORderUseCase;

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) {
    	orderRequest.assignUserId(this.getUserFromJwt().getId());
    	Order order = creatorOrderUseCase.createOrder(orderRequest);
        URI location = URI.create("/items?order_id=" + order.getId());
    	return ResponseEntity.created(location).body(new OrderResponse(order, "201", "Resource successfully created"));
    }

    @PostMapping(path = "/{orderId}/items", produces = "application/json", consumes = "application/json")
    public ResponseEntity<ItemResponse> createItem(@RequestBody ItemOrderRequest itemOrderRequest, @PathVariable("orderId") String orderID) {
    	addItemToOrderUseCase.addItemToOrder(orderID, itemOrderRequest);
        return ResponseEntity.ok().body(new ItemResponse(itemOrderRequest.getId()));
    }

    @GetMapping(path = "/{orderId}/items", produces = "application/json")
    public ResponseEntity<ItemsResponse> getItems(@PathVariable("orderId") String orderId) {
        return ResponseEntity.ok().body(new ItemsResponse(getItemFromORderUseCase.getItemsFromOrder(orderId)));
    }

    @GetMapping(path = "", produces = "application/json")
    public ResponseEntity<OrdersResponse> getOrders() {
        List<OrderOld> orders = getOrdersFromUser.getOrdersFromUser(this.getUserFromJwt().getId());
        List<OrderOld> ordersShared = getOrdersFromUser.getOrdersFromUserInvited(this.getUserFromJwt().getUsername());
        orders.addAll(ordersShared);

        var mappedOrders = orders
                .stream()
                .map(order -> new OrderDto(order.getId(), order.getUserId(), order.getStatus().toString(), order.getItems().size() > 0, order.getName()))
                .collect(Collectors.toList());

        return ResponseEntity
                .ok()
                .body(new OrdersResponse(mappedOrders));
    }
    
//	@PatchMapping(path = "/{orderId}",  produces = "application/json", consumes = "application/json")
//    public ResponseEntity<Response> updateOrder(@PathVariable("orderId") String orderId) {
//		updateOrderUC.updateStatusOrder(orderId, this.getUserFromJwt().getId(), OrderStatus.CLOSED);
//		return ResponseEntity.ok().body(new Response());
//    }
//    
//	@PatchMapping(path = "/{orderId}/users",  produces = "application/json", consumes = "application/json")
//    public ResponseEntity<OrderResponseOld> updateOrderForShared(@PathVariable("orderId") String orderId) {
//		return ResponseEntity.ok().body(new OrderResponseOld(updateOrderUC.updateOrderForShared(orderId, this.getUserFromJwt().getUsername())));
//    }
//	
//	@PatchMapping(path = "/{orderId}/items/{itemId}", produces = "application/json", consumes = "application/json")
//    public ResponseEntity<Response> updateItemOrder(@RequestBody UpdateQuantity quantity, @PathVariable("orderId") String orderId, @PathVariable("itemId") String itemId) {
//		updateItemOrderUC.updateItemOrder(orderId, itemId, quantity.getQuantity());
//		return ResponseEntity.ok().body(new Response());
//    }
//
//
//    @DeleteMapping("/{orderId}/items/{itemId}")
//    public ResponseEntity<Response> removeItem(@PathVariable("orderId") String orderID, @PathVariable("itemId") String itemID) {
//        removeItemFromOrderUC.removeItemFromOrder(orderID, itemID);
//		return ResponseEntity.ok().body(new Response());
//    }

}
