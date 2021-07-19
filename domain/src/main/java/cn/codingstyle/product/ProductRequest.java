package cn.codingstyle.product;

import lombok.Builder;
import lombok.ToString;

import java.math.BigDecimal;

@Builder
@ToString
public class ProductRequest {
    private String barcode;
    private String name;
    private String unit;
    private BigDecimal price;
    private String type;

    public Product toEntity() {
        return Product.builder()
                .name(name)
                .barcode(barcode)
                .price(price)
                .type(type)
                .unit(unit)
                .build();
    }
}
