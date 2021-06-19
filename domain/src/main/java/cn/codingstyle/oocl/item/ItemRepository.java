package cn.codingstyle.oocl.item;

import java.util.Optional;

public interface ItemRepository {
    void save(Item item);

    Item findByBarcode(String barcode);

    Optional<Item> findById(Integer id);

    void deleteById(Integer id);
}
