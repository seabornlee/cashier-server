package cn.codingstyle.product;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Optional<ProductItem> first = this.productItems.stream()
                .filter(item -> item.getBarcode().equals(productItem.getBarcode()))
                .findFirst();
        if (first.isPresent()) {
            ProductItem item = first.get();
            item.increase(productItem.getQuantity());
        } else {
            this.productItems.add(productItem);
        }
    }
}
