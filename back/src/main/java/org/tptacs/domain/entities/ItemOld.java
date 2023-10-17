package org.tptacs.domain.entities;

import java.math.BigDecimal;
import java.util.Objects;

import org.tptacs.presentation.dto.ItemDto;

import lombok.Getter;

@Getter
public class ItemOld {
    private String id;
    private String name;
    private BigDecimal price;
    private String picture;

    public ItemOld(String id, String name, BigDecimal price, String picture) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.picture = picture;
    }

    private ItemOld() {}

	public ItemDto toDto() {
		ItemDto itemDto = new ItemDto(id, name, price, picture);
		return itemDto;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemOld item = (ItemOld) o;
        return Objects.equals(id, item.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
