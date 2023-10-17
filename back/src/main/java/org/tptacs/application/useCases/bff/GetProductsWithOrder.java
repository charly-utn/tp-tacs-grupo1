package org.tptacs.application.useCases.bff;

import org.springframework.stereotype.Service;
import org.tptacs.domain.entities.ItemOld;
import org.tptacs.domain.entities.ItemOrderOld;
import org.tptacs.infraestructure.repositories.interfaces.IItemsRepository;
import org.tptacs.infraestructure.repositories.interfaces.IOrderRepository;
import java.util.ArrayList;
import java.util.List;

@Service
public class GetProductsWithOrder {
    private final IItemsRepository itemsRepository;
    private final IOrderRepository orderRepository;

    public GetProductsWithOrder(IItemsRepository itemsRepository, IOrderRepository orderRepository) {
        this.itemsRepository = itemsRepository;
        this.orderRepository = orderRepository;
    }
    public List<ItemOrderOld> getProductsWithOrder(String orderId) {
        var items = itemsRepository.getAll();
        if (orderId == null) return items.stream().map(i -> new ItemOrderOld(i, 0L)).toList();

        var order = this.orderRepository.get(orderId);
        var itemsInOrder = order.findItemsOrder(items.stream().map(ItemOld::getId).toList());

        var itemsOutOfOrder = items.stream()
                .filter(i -> !itemsInOrder.stream().map(ItemOrderOld::getItem).toList().contains(i))
                .map(i -> new ItemOrderOld(i,0L)).toList();

        var result = new ArrayList<ItemOrderOld>();
        result.addAll(itemsInOrder);
        result.addAll(itemsOutOfOrder);
        return result;
    }
}
