package cn.codingstyle.oocl.application.item;

import cn.codingstyle.oocl.item.Item;
import cn.codingstyle.oocl.item.ItemService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public void createItem(@RequestBody ItemForm itemForm) {
        itemService.newItem(itemForm.toRequest());
    }

    @GetMapping("/{barcode}")
    public Item getItem(@PathVariable String barcode) {
        return itemService.getItem(barcode);
    }

    @PutMapping("/{id}")
    public void updateItem(@PathVariable Integer id, @RequestBody ItemForm itemForm) {
        itemService.updateItem(id, itemForm.toRequest());
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Integer id) {
        itemService.deleteItem(id);
    }
}
