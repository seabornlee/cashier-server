package cn.codingstyle.server.item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {
    void save(Item item);

    Item findByBarcode(String barcode);

    Optional<Item> findById(Integer id);

    void deleteById(Integer id);

    List<Item> findAll();
}
