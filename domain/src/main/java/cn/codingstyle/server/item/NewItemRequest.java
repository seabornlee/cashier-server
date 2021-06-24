package cn.codingstyle.server.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewItemRequest {
    private String barcode;
    private String name;
    private String unit;
    private BigDecimal price;
    private String type;

    public Item toEntity() {
        return Item.builder()
                .name(name)
                .barcode(barcode)
                .price(price)
                .type(type)
                .unit(unit)
                .build();
    }

}
