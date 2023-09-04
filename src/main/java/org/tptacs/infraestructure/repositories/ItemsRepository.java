package org.tptacs.infraestructure.repositories;

import org.springframework.stereotype.Repository;
import org.tptacs.domain.entities.Item;
import org.tptacs.domain.exceptions.NotFoundException;
import org.tptacs.infraestructure.repositories.interfaces.IItemsRepository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ItemsRepository implements IItemsRepository {
    private final static Map<String, Item> repository = new HashMap<>();
    static {
        repository.put("abcd", new Item("abcd", "Papas Fritas", new BigDecimal(300)));
        repository.put("defg", new Item("defg", "Empanada de Carne", new BigDecimal(400)));
        repository.put("hijk", new Item("hijk", "Hamburguesa", new BigDecimal(3500)));
    }

    public void save(Item item) {
        repository.put(item.getId(), item);
    }

    public Item get(String id) {
        var item = repository.get(id);
        if (item == null) throw new NotFoundException(id, "item");
        return repository.get(id);
    }

    @Override
    public void exists(String id) {
        if (!repository.containsKey(id)) throw new NotFoundException(id, "item");
    }
}
