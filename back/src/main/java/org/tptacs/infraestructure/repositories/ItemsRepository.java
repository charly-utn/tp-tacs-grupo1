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
        repository.put("abcd", new Item(
                "abcd",
                "Papas Fritas",
                new BigDecimal(300),
                "https://images.unsplash.com/photo-1630384060421-cb20d0e0649d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1450&q=80"));
        repository.put("defg", new Item(
                "defg",
                "Empanada de Carne",
                new BigDecimal(400),
                "https://media.istockphoto.com/id/1411820064/es/foto/empanada-argentina-aislada-sobre-fondo-blanco.jpg?s=1024x1024&w=is&k=20&c=cMMmEqtcIU3YbPmK4_RKzUeRB0NPodKiHFd_dqk7y9w="));
        repository.put("hijk", new Item(
                "hijk",
                "Hamburguesa",
                new BigDecimal(3500),
                "https://media.istockphoto.com/id/1309352410/es/foto/hamburguesa-con-queso-con-tomate-y-lechuga-en-tabla-de-madera.webp?s=1024x1024&w=is&k=20&c=eD-BBjoFkwriCJkrPXpl07TrIzLTJs00BciR9oJ9A_g="));
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
