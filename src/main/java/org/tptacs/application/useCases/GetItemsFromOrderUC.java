package org.tptacs.application.useCases;

import org.springframework.stereotype.Service;
import org.tptacs.domain.entities.ItemOrder;
import org.tptacs.infraestructure.repositories.interfaces.IOrderRepository;

import jakarta.validation.ValidationException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GetItemsFromOrderUC {
    private final IOrderRepository orderRepository;

    public GetItemsFromOrderUC(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<ItemOrder> getItemsFromOrder(String orderId) {
        var order = this.orderRepository.get(orderId);
        return order.getItems();
    }
    
    public ItemOrder getItemFromOrder(String orderId, String itemId) {
		Optional<ItemOrder> itemOp = orderRepository.get(orderId).getItems().stream().filter(item -> item.getItem().getId().equals(itemId)).collect(Collectors.toList()).stream().findFirst();
		if(itemOp.isEmpty()) throw new ValidationException("El item debe haber sido previamente agregado");
		return itemOp.get(); 
    }
}
