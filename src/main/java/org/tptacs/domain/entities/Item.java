package org.tptacs.domain.entities;

import lombok.Getter;

@Getter
public class Item {
    private String id;
    private String name;
    private Double price;

    public Item(String id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
