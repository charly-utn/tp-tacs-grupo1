package org.tptacs.domain.entities;


import java.time.LocalDateTime;
import java.util.List;

import org.tptacs.domain.enums.OrderStatus;
import org.tptacs.domain.exceptions.ResourceNotFoundException;

import jakarta.validation.ValidationException;
import lombok.Getter;

@Getter
public class Order {
    private String id;
    private String userId;
    private List<ItemOrder> items;
    private OrderStatus status;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdate;

    public Order(String id, String userId, List<ItemOrder> item, OrderStatus status) {
        this.id = id;
        this.userId = userId;
        this.items = item;
        this.status = status;
        this.creationDate = LocalDateTime.now();
        this.lastUpdate = LocalDateTime.now();
    }

    private Order() {}

    public void addItem(ItemOrder itemOrder){
        this.lastUpdate = LocalDateTime.now();
        items.add(itemOrder);
    }

	public void upateStatus(OrderStatus status) {
		if(this.getStatus().equals(status)) {
			throw new ValidationException("El pedido no se puede cambiar al mismo estado");
		}
		this.lastUpdate = LocalDateTime.now();
		this.status = status;
		
	}


    public void removeItem(Item item){
        var result = this.items.removeIf(io -> io.getItem().getId().equals(item.getId()));
        this.lastUpdate = LocalDateTime.now();
        if(!result) throw new ResourceNotFoundException(item.getId(), "item");
    }

    public List<ItemOrder> findItemsOrder(List<String> itemIds) {
        return this.getItems().stream()
                .filter(i -> itemIds.contains(i.getItem().getId())).toList();
    }

}
