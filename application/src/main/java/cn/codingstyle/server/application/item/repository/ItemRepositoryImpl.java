package cn.codingstyle.server.application.item.repository;

import cn.codingstyle.server.item.Item;
import cn.codingstyle.server.item.ItemRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ItemRepositoryImpl implements ItemRepository {
    private final ItemDAO itemDAO;

    public ItemRepositoryImpl(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    @Override
    public void save(Item item) {
        itemDAO.save(ItemDO.from(item));
    }

    @Override
    public Item findByBarcode(String barcode) {
        return itemDAO.findByBarcode(barcode);
    }

    @Override
    public Optional<Item> findById(Integer id) {
        return itemDAO.findById(id).map(ItemDO::toEntity);
    }

    @Override
    public void deleteById(Integer id) {
        itemDAO.deleteById(id);
    }
}
