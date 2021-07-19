package cn.codingstyle.product;

import java.util.List;

public interface ScanProductItemRepository {

    List<ProductItem> findAll();

    void add(ProductItem productItem);
}
