package cn.codingstyle.server.application.item;

import cn.codingstyle.server.item.NewItemRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemForm {
    private String barcode;
    private String name;
    private String unit;
    private BigDecimal price;
    private String type;

    public NewItemRequest toRequest() {
        return NewItemRequest.builder()
                .name(name)
                .barcode(barcode)
                .price(price)
                .type(type)
                .unit(unit)
                .build();
    }
}
