package org.tptacs.application.useCases;

import org.springframework.stereotype.Service;
import org.tptacs.domain.entities.ItemOrder;
import org.tptacs.infraestructure.repositories.interfaces.IItemsRepository;
import org.tptacs.infraestructure.repositories.interfaces.IOrderRepository;
import org.tptacs.presentation.requestModels.ItemOrderRequest;

import jakarta.validation.ValidationException;

@Service
public class AddItemToOrderUC {
    private final IItemsRepository itemsRepository;
    private final IOrderRepository orderRepository;

    public AddItemToOrderUC(IItemsRepository itemsRepository, IOrderRepository orderRepository) {
        this.itemsRepository = itemsRepository;
        this.orderRepository = orderRepository;
    }

    public void addItemToOrder(String orderId, ItemOrderRequest orderRequest) {
        var order = this.orderRepository.get(orderId);
        
        Boolean isntRep = order.getItems().stream().noneMatch( i -> i.getItem().getId().equals(orderRequest.getId()));
        if(isntRep) throw new ValidationException("El item ya se encuentra en el pedido");
        
        var item = this.itemsRepository.get(orderRequest.getId());

        var itemOrder = new ItemOrder(item, orderRequest.getQuantity());

        order.addItem(itemOrder);

        this.orderRepository.update(order);
    }
}