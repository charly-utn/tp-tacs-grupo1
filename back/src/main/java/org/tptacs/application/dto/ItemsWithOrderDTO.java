package org.tptacs.application.dto;


import java.math.BigDecimal;

import org.tptacs.domain.entities.Product;

public class ItemsWithOrderDTO {
    private String id;
    private String name;
    private BigDecimal price;
    private Long quantity;
    private String picture;

    public ItemsWithOrderDTO(Long quantity, Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.quantity = quantity;
        this.picture = product.getPicture();
    }
}
