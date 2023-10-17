package org.tptacs.infraestructure.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tptacs.domain.entities.Order;

public interface OrderRepository extends MongoRepository<Order, String> {

	List<Order> findByUserId(String userId);

}
