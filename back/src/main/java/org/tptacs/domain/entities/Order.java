package org.tptacs.domain.entities;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.tptacs.domain.enums.OrderStatus;
import org.tptacs.domain.exceptions.NotFoundException;
import org.tptacs.domain.exceptions.ResourceNotFoundException;

import jakarta.validation.ValidationException;
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
        this.lastUpdate = LocalDateTime.now();
		if (this.items != null) this.items.add(itemOrder); 
		else this.items = List.of(itemOrder);
	}
	
	public void updateStatus(OrderStatus status) {
		if(this.getStatus().equals(status)) {
			throw new ValidationException("El pedido no se puede cambiar al mismo estado");
		}
		this.lastUpdate = LocalDateTime.now();
		this.status = status;	
	}
	
    public void addUserInvited(String userName) {
    	if(this.getUsersInvited().contains(userName)) {
    		//TODO tiene sentido pensar que esta volviendo a agregar el mismo id? tiramos un error?
    	}else {
    		this.getUsersInvited().add(userName);
    	}
    }

	public ItemOrder getItemFromOrder(String productId) {
        Optional<ItemOrder> itemOp = this.items.stream().filter(item -> item.getProduct().getId().equals(productId)).findFirst();
        if(itemOp.isEmpty()) throw new NotFoundException("El item buscado no existe en este pedido", "Item Order");
        return itemOp.get();	}

	public void updateItemOrder(ItemOrder item) {
        Optional<ItemOrder> itemOp = this.items.stream().filter( i -> i.getProduct().getId().equals(item.getProduct().getId())).findFirst();
        if (itemOp.isEmpty()) throw new NotFoundException("Debe agregar el item al pedido antes de actualizarlo", "Item Order");
        itemOp.get().updateQuantity(item.getQuantity());
	}

	public void removeItem(Product item) {
        var result = this.items.removeIf(io -> io.getProduct().getId().equals(item.getId()));
        this.lastUpdate = LocalDateTime.now();
        if(!result) throw new ResourceNotFoundException(item.getId(), "item");		
	}

    public List<ItemOrder> findItemsOrder(List<String> itemIds) {
        return this.getItems().stream()
                .filter(i -> itemIds.contains(i.getProduct().getId())).toList();
    }

  
	
    
}
