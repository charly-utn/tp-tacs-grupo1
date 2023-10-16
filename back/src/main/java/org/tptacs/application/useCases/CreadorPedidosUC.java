package org.tptacs.application.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tptacs.domain.entities.Pedido;
import org.tptacs.infraestructure.repositories.PedidoRepository;

@Service
public class CreadorPedidosUC {
	
    @Autowired
    private PedidoRepository pedido;

	public Pedido createPedido() {
		Pedido ped = new Pedido();
		pedido.save(ped);
		return ped;
	}
}
