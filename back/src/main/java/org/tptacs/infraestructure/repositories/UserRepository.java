package org.tptacs.infraestructure.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tptacs.domain.entities.User;

public interface UserRepository extends MongoRepository<User, String> {

}

