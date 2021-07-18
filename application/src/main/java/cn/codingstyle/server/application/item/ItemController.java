package cn.codingstyle.server.application.item;

import cn.codingstyle.server.item.Item;
import cn.codingstyle.server.item.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@Api
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    @ApiOperation("创建商品")
    public void createItem(@RequestBody ItemForm itemForm) {
        itemService.newItem(itemForm.toRequest());
    }

    @ApiOperation("获取商品")
    @GetMapping("/{barcode}")
    public Item getItem(@PathVariable String barcode) {
        return itemService.getItem(barcode);
    }

    @ApiOperation("获取商品列表")
    @GetMapping
    public List<Item> getItems() {
        return itemService.getItems();
    }

    @ApiOperation("更新商品")
    @PutMapping("/{id}")
    public void updateItem(@PathVariable Integer id, @RequestBody ItemForm itemForm) {
        itemService.updateItem(id, itemForm.toRequest());
    }

    @ApiOperation("删除商品")
    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Integer id) {
        itemService.deleteItem(id);
    }
}
