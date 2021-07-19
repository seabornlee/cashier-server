package cn.codingstyle.product;

import java.util.ArrayList;
import java.util.List;

public class ScanProductItemRepositoryDouble implements ScanProductItemRepository {
    private List<ProductItem> products = new ArrayList<>();

    @Override
    public List<ProductItem> findAll() {
        return products;
    }

    @Override
    public void add(ProductItem productItem) {
        this.products.add(productItem);
    }
}
