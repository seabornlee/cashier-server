package cn.codingstyle.product;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ProductReceipt {
    private List<ProductItem> products;
    private BigDecimal totalAmount;

    public ProductReceipt(List<ProductItem> products) {
        this.products = products;
        this.totalAmount = this.products.stream()
                .map(ProductItem::totalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
