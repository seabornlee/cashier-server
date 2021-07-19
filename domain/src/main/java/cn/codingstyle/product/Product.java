package cn.codingstyle.product;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Builder
@Getter
@ToString
public class Product {
    private int id;
    private String barcode;
    private String name;
    private String unit;
    private BigDecimal price;
    private String type;
}
