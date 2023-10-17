package org.tptacs.infraestructure.repositories.interfaces;

import org.tptacs.domain.entities.ItemOld;

import java.util.List;

public interface IItemsRepository {
    void save(ItemOld item);
    ItemOld get(String id);
    void exists(String id);
    List<ItemOld> getAll();
}
