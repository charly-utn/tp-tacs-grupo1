package org.tptacs.presentation.controllers.bff;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tptacs.application.useCases.bff.GetProductsWithOrder;
import org.tptacs.domain.entities.ItemOrder;
import org.tptacs.presentation.controllers.BaseController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Orders")
@RequestMapping(value = "/api/items")
public class ItemsController extends BaseController {

	@Autowired
    private GetProductsWithOrder getProductsWithOrder;


    @GetMapping("")
    public ResponseEntity<List<ItemOrder>> getProducts(@RequestParam(value = "order_id", required = false) String orderId) {
        return ResponseEntity.ok(getProductsWithOrder.getProductsWithOrder(orderId));
    }
}
