package org.tptacs.infraestructure.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tptacs.domain.entities.Pedido;

public interface PedidoRepository extends MongoRepository<Pedido, String> {

}
