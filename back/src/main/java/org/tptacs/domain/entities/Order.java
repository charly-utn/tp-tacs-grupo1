package org.tptacs.domain.entities;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.tptacs.domain.enums.OrderStatus;

import lombok.Getter;

@Getter
@Document(collection = "orders")
public class Order {

    @Id
    private String id;
    private String userId;
    private String name;
    private List<ItemOrder> items;
    private OrderStatus status;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdate;
    private List<String> usersInvited = new LinkedList<String>();

    public Order(String id, String userId, String name, List<ItemOrder> item, OrderStatus status) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.items = item;
        this.status = status;
        this.creationDate = LocalDateTime.now();
        this.lastUpdate = LocalDateTime.now();
    }
    
	public Order() {
	}

	public void addItem(ItemOrder itemOrder) {
		if (this.items != null) this.items.add(itemOrder);
		else this.items = List.of(itemOrder);
	}
  
	
    
}
