package cn.codingstyle.oocl.application.item;

import cn.codingstyle.oocl.item.ItemRepository;
import cn.codingstyle.oocl.item.ItemService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemBeanConfig {
    @Bean
    public ItemService itemService(ItemRepository itemRepository) {
        return new ItemService(itemRepository);
    }
}
