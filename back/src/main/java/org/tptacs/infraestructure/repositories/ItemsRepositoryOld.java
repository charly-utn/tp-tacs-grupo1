package org.tptacs.infraestructure.repositories;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;
import org.tptacs.domain.entities.ItemOld;
import org.tptacs.domain.exceptions.ResourceNotFoundException;
import org.tptacs.infraestructure.repositories.interfaces.IItemsRepository;

@Repository
public class ItemsRepositoryOld extends FileRepository<ItemOld> implements IItemsRepository {
    public ItemsRepositoryOld() {
        super(ItemsRepositoryOld.class.getSimpleName(), ItemOld.class);
        if (count() == 0) {
            loadMockData();
        }
    }

    public void save(ItemOld item) {
        super.put(item.getId(), item);
    }

    public ItemOld get(String id) {
        var item = super.get(id);
        if (item == null) throw new ResourceNotFoundException(id, "item");
        return item;
    }

    @Override
    public void exists(String id) {
        if (!super.exist(id)) throw new ResourceNotFoundException(id, "item");
    }

    @Override
    public List<ItemOld> getAll() {
        return values();
    }

    @SneakyThrows
    private void loadMockData() {
        var path = getRepositoryPath("/mocks/ItemsData.json");
        var file = new File(path);
        List<ItemOld> itemsMock = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, ItemOld.class));
        itemsMock.forEach(this::save);
    }
}
