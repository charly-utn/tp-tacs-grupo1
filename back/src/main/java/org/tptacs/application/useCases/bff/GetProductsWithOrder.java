package org.tptacs.application.useCases.bff;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tptacs.domain.entities.ItemOrder;
import org.tptacs.domain.entities.Product;
import org.tptacs.infraestructure.repositories.OrderRepository;
import org.tptacs.infraestructure.repositories.ProductRepository;

@Service
public class GetProductsWithOrder {
    
	@Autowired
	private ProductRepository productRepository;
    
	@Autowired
	private OrderRepository orderRepository;

    public List<ItemOrder> getProductsWithOrder(String orderId) {
        var items = productRepository.findAll();
        if (orderId == null) return items.stream().map(i -> new ItemOrder(i, 0L)).toList();

        var order = orderRepository.findById(orderId);
        var itemsInOrder = order.get().findItemsOrder(items.stream().map(Product::getId).toList());

        var itemsOutOfOrder = items.stream()
                .filter(i -> !itemsInOrder.stream().map(ItemOrder::getProduct).toList().contains(i))
                .map(i -> new ItemOrder(i,0L)).collect(Collectors.toList());

        var result = new ArrayList<ItemOrder>();
        result.addAll(itemsInOrder);
        result.addAll(itemsOutOfOrder);
        return result;
    }
}
