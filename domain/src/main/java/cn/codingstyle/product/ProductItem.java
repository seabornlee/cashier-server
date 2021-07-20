package cn.codingstyle.product;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductItem {
    private String barcode;
    private String name;
    private String unit;
    private Integer quantity;
    private BigDecimal price;

    public ProductItem(Product product, int quantity) {
        this.name = product.getName();
        this.unit = product.getUnit();
        this.quantity = quantity;
        this.price = product.getPrice();
        this.barcode = product.getBarcode();
    }

    public ProductItem(String name, String unit, Integer quantity, BigDecimal price) {
        this.name = name;
        this.unit = unit;
        this.quantity = quantity;
        this.price = price;
    }

    public BigDecimal totalAmount() {
        return this.price.multiply(BigDecimal.valueOf(this.quantity));
    }

    public void increase(Integer quantity) {
        this.quantity += quantity;
    }
}
