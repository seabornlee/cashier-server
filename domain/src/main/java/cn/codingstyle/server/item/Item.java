package cn.codingstyle.server.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
public class Item {
    private int id;
    private String barcode;
    private String name;
    private String unit;
    private BigDecimal price;
    private String type;

    public void update(String barcode, String name, BigDecimal price, String type, String unit) {
        this.barcode = barcode;
        this.name = name;
        this.price = price;
        this.type = type;
        this.unit = unit;
    }
}
