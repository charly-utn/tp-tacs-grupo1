package org.tptacs.domain.entities;

import lombok.Getter;
import org.tptacs.domain.enums.OrderStatus;
import org.tptacs.domain.exceptions.NotFoundException;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

@Getter
public class Order {
    private String id;
    private Long userId;
    private List<ItemOrder> items;
    private OrderStatus status;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdate;

    public Order(String id, Long userId, List<ItemOrder> item, OrderStatus status) {
        this.id = id;
        this.userId = userId;
        this.items = item;
        this.status = status;
        this.creationDate = LocalDateTime.now();
        this.lastUpdate = LocalDateTime.now();
    }

    public void addItem(ItemOrder itemOrder){
        this.lastUpdate = LocalDateTime.now();
        items.add(itemOrder);
    }

    public void removeItem(Item item){
        var result = this.items.removeIf(io -> io.getItem().getId().equals(item.getId()));
        this.lastUpdate = LocalDateTime.now();
        if(!result) throw new NotFoundException(item.getId(), "item");
    }
}
