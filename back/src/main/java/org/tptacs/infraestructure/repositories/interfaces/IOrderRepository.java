package org.tptacs.infraestructure.repositories.interfaces;

import org.tptacs.domain.entities.Order;

public interface IOrderRepository {
    void save(Order order);
    Order get(String id);
    void exists(String id);
    void update(Order order);
    Long count();
}
