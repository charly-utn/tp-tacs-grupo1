package org.tptacs.domain.entities;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.tptacs.presentation.dto.ItemDto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Document(collection = "products")
public class Product {
	
	@Id
    private String id;
    private String name;
    private BigDecimal price;
    private String picture;
    
    public Product(String id, String name, BigDecimal price, String picture) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.picture = picture;
    }
    
	public ItemDto toDto() {
		ItemDto itemDto = new ItemDto(id, name, price, picture);
		return itemDto;
	}
	
}
