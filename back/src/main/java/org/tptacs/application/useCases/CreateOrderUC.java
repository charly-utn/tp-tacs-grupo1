package org.tptacs.application.useCases;

import org.springframework.stereotype.Service;
import org.tptacs.domain.entities.ItemOrder;
import org.tptacs.domain.entities.Order;
import org.tptacs.domain.enums.OrderStatus;
import org.tptacs.infraestructure.repositories.interfaces.IItemsRepository;
import org.tptacs.infraestructure.repositories.interfaces.IOrderRepository;
import org.tptacs.presentation.requestModels.OrderRequest;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CreateOrderUC {
    private final IItemsRepository itemsRepository;
    private final IOrderRepository orderRepository;

    public CreateOrderUC(IItemsRepository itemsRepository, IOrderRepository orderRepository) {
        this.itemsRepository = itemsRepository;
        this.orderRepository = orderRepository;
    }

    public Order createOrder(OrderRequest orderRequest) {
        var items = orderRequest.getItems().stream().map(ior -> {
            var item = this.itemsRepository.get(ior.getId());
            return new ItemOrder(item, ior.getQuantity());
        }).collect(Collectors.toList());

        var order = new Order(UUID.randomUUID().toString(), orderRequest.getUserId(), orderRequest.getName(), items, OrderStatus.NEW);
        this.orderRepository.save(order);
        return order;
    }
}
