package cn.codingstyle.oocl.application.item.repository;

import cn.codingstyle.oocl.item.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemDAO extends CrudRepository<ItemDO, Integer> {
    Item findByBarcode(String barcode);
}
