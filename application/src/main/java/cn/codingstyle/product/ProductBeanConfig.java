package cn.codingstyle.product;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductBeanConfig {
    @Bean
    public ProductService productService(ProductRepository productRepository, ScanProductItemRepository scanProductItemRepository) {
        return new ProductService(productRepository, scanProductItemRepository);
    }
}
