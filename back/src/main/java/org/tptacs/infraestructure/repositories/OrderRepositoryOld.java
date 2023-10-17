package org.tptacs.infraestructure.repositories;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;
import org.tptacs.domain.entities.OrderOld;
import org.tptacs.domain.exceptions.NotFoundException;
import org.tptacs.infraestructure.repositories.interfaces.IOrderRepository;

@Repository
public class OrderRepositoryOld extends FileRepository<OrderOld> implements IOrderRepository {
    public OrderRepositoryOld() {
        super(OrderRepositoryOld.class.getSimpleName(), OrderOld.class);
    }

    public void save(OrderOld order) {
        super.put(order.getId(), order);
    }

    public OrderOld get(String id) {
        var item = super.get(id);
        if (item == null) throw new NotFoundException(id, "pedido");
        return super.get(id);
    }

    public void update(OrderOld order){
        super.replace(order.getId(), order);
    }

    @Override
    public void exists(String id) {
        if (!super.exist(id)) throw new NotFoundException(id, "pedido");
    }
    
    public Long count() {
    	return super.count();
    }

    @Override
    public List<OrderOld> getOrdersFromUser(String userId) {
        return values().stream().filter(order -> order.getUserId().equals(userId)).collect(Collectors.toList());
    }

}
