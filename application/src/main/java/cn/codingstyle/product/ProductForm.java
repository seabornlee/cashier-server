package cn.codingstyle.product;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProductForm {
    private String barcode;
    private String name;
    private String unit;
    private BigDecimal price;
    private String type;

    public ProductRequest toRequest() {
        return ProductRequest.builder()
                .barcode(barcode)
                .name(name)
                .unit(unit)
                .price(price)
                .type(type)
                .build();
    }

}
