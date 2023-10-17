package org.tptacs.infraestructure.repositories.interfaces;

import org.tptacs.domain.entities.OrderOld;

import java.util.List;

public interface IOrderRepository {
    void save(OrderOld order);
    OrderOld get(String id);
    void exists(String id);
    void update(OrderOld order);
    Long count();
    List<OrderOld> getOrdersFromUser(String userId);
}
