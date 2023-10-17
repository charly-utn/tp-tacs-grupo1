package org.tptacs.infraestructure.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tptacs.domain.entities.Order;

public interface OrderRepository extends MongoRepository<Order, String> {

}
