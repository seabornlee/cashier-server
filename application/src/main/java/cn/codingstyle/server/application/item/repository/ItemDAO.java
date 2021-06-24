package cn.codingstyle.server.application.item.repository;

import cn.codingstyle.server.item.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemDAO extends CrudRepository<ItemDO, Integer> {
    Item findByBarcode(String barcode);
}
