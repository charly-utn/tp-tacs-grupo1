package org.tptacs.infraestructure.repositories;

import org.springframework.stereotype.Repository;
import org.tptacs.domain.entities.Order;
import org.tptacs.domain.exceptions.NotFoundException;
import org.tptacs.domain.exceptions.ResourceNotFoundException;
import org.tptacs.infraestructure.repositories.interfaces.IOrderRepository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class OrderRepository implements IOrderRepository {
    private final Map<String, Order> repository = new HashMap<>();

    public void save(Order order) {
        this.repository.put(order.getId(), order);
    }

    public Order get(String id) {
        var item = this.repository.get(id);
        if (item == null) throw new ResourceNotFoundException(id, "pedido");
        return this.repository.get(id);
    }

    public void update(Order order){
        repository.replace(order.getId(), order);
    }

    @Override
    public void exists(String id) {
        if (!this.repository.containsKey(id)) throw new ResourceNotFoundException(id, "pedido");
    }
    
    public Long count() {
    	return Long.valueOf(repository.values().size());
    }
    
}
