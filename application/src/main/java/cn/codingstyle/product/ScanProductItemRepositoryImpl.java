package cn.codingstyle.product;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ScanProductItemRepositoryImpl implements ScanProductItemRepository {
    private final List<ProductItem> productItems;

    public ScanProductItemRepositoryImpl() {
        this.productItems = new ArrayList<>();
    }

    @Override
    public List<ProductItem> findAll() {
        return this.productItems;
    }

    @Override
    public void add(ProductItem productItem) {
        this.productItems.add(productItem);
    }
}
