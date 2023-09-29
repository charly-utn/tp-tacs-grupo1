package org.tptacs.infraestructure.repositories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;
import org.tptacs.domain.entities.Item;
import org.tptacs.domain.exceptions.ResourceNotFoundException;
import org.tptacs.infraestructure.repositories.interfaces.IItemsRepository;

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
        if (item == null) throw new ResourceNotFoundException(id, "item");
        return repository.get(id);
    }

    @Override
    public void exists(String id) {
        if (!repository.containsKey(id)) throw new ResourceNotFoundException(id, "item");
    }

    @Override
    public List<Item> getAll() {
        return new ArrayList<>(repository.values());
    }
}
