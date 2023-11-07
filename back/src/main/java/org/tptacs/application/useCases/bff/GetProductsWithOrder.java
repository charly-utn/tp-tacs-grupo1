package org.tptacs.application.useCases.bff;

import org.springframework.stereotype.Service;
import org.tptacs.domain.entities.Item;
import org.tptacs.domain.entities.ItemOrder;
import org.tptacs.infraestructure.repositories.interfaces.IItemsRepository;
import org.tptacs.infraestructure.repositories.interfaces.IOrderRepository;
import org.tptacs.presentation.dto.ItemOrderDto;
import org.tptacs.presentation.requestModels.ItemOrderRequest;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingLong;

@Service
public class GetProductsWithOrder {
    private final IItemsRepository itemsRepository;
    private final IOrderRepository orderRepository;

    public GetProductsWithOrder(IItemsRepository itemsRepository, IOrderRepository orderRepository) {
        this.itemsRepository = itemsRepository;
        this.orderRepository = orderRepository;
    }
    public List<ItemOrderDto> getProductsWithOrder(String orderId, String userId) {
        var items = itemsRepository.getAll();
        if (orderId == null) return items.stream().map(i -> new ItemOrderDto(null, null, i.toDto(), 0L, 0L)).toList();

        var order = this.orderRepository.get(orderId);
        var itemsInOrder = order.findItemsOrder(items.stream().map(Item::getId).toList());

        var totalByItem = itemsInOrder.stream().collect(groupingBy(ItemOrder::getItemId, summingLong(ItemOrder::getQuantity)));

        var itemsInOrderWithTotal = itemsInOrder.stream()
                .map(i -> {
                    if (totalByItem.containsKey(i.getItemId())) {
                        return new ItemOrderDto(i.getId(), userId, i.getItem().toDto(), i.getQuantity(), totalByItem.get(i.getItemId()));
                    }
                    return new ItemOrderDto(null, null, i.getItem().toDto(), i.getQuantity(), totalByItem.get(i.getItemId()));
                }).toList();

        var itemsOutOfOrder = items.stream()
                .filter(i -> !itemsInOrder.stream().map(ItemOrder::getItem).toList().contains(i))
                .map(i -> new ItemOrderDto(null,null, i.toDto(),0L, 0L)).toList();

        var result = new ArrayList<ItemOrderDto>();
        result.addAll(itemsInOrderWithTotal);
        result.addAll(itemsOutOfOrder);
        return result;
    }
}
