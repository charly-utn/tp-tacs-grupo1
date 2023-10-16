package org.tptacs.domain.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pedidos")
public class Pedido {

    @Id
    private String id;
    private String string;
    
	public Pedido() {
	}

	public Pedido(String id, String string) {
		super();
		this.id = id;
		this.string = string;
	}
	
	@Override
	public String toString() {
		return String.format(
	        "Pedido[id=%s, string='%s']",
	        id, string);
	}

    
	
    
}
