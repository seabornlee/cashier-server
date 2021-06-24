package cn.codingstyle.server.item;

public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void newItem(NewItemRequest request) {
        itemRepository.save(request.toEntity());
    }

    public Item getItem(String barcode) {
        return itemRepository.findByBarcode(barcode);
    }

    public void updateItem(Integer id, NewItemRequest request) {
        itemRepository.findById(id)
                .ifPresent(item -> {
                    item.update(request.getBarcode(), request.getName(),
                            request.getPrice(), request.getType(), request.getUnit());
                    itemRepository.save(item);
                });
    }

    public void deleteItem(Integer id) {
        itemRepository.deleteById(id);
    }
}
