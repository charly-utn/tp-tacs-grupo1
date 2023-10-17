package org.tptacs.infraestructure.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tptacs.domain.entities.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

}
