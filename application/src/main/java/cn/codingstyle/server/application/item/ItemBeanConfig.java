package cn.codingstyle.server.application.item;

import cn.codingstyle.server.item.ItemRepository;
import cn.codingstyle.server.item.ItemService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemBeanConfig {
    @Bean
    public ItemService itemService(ItemRepository itemRepository) {
        return new ItemService(itemRepository);
    }
}
