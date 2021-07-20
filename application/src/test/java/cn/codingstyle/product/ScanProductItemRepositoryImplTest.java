package cn.codingstyle.product;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ScanProductItemRepositoryImplTest {

    @Test
    void should_increase_item_when_item_is_exist() {
        ScanProductItemRepositoryImpl scanProductItemRepository = new ScanProductItemRepositoryImpl();
        Product product = Product.builder()
                .barcode("12345678")
                .price(new BigDecimal(15))
                .type("1")
                .unit("piece")
                .build();
        ProductItem productItem = new ProductItem(product, 1);
        scanProductItemRepository.add(productItem);
        assertThat(scanProductItemRepository.findAll().size()).isEqualTo(1);

        scanProductItemRepository.add(productItem);
        List<ProductItem> all = scanProductItemRepository.findAll();
        assertThat(all.size()).isEqualTo(1);
        assertThat(all.get(0).getQuantity()).isEqualTo(2);

    }
}
