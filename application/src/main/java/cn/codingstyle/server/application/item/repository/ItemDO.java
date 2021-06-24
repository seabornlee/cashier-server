package cn.codingstyle.server.application.item.repository;


import cn.codingstyle.server.item.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "item")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String barcode;
    private String name;
    private String unit;
    private BigDecimal price;
    private String type;

    public static ItemDO from(Item item) {
        return builder()
                .id(item.getId())
                .name(item.getName())
                .barcode(item.getBarcode())
                .price(item.getPrice())
                .type(item.getType())
                .unit(item.getUnit())
                .build();
    }

    public Item toEntity() {
        return Item.builder()
                .id(id)
                .name(name)
                .barcode(barcode)
                .price(price)
                .type(type)
                .unit(unit)
                .build();
    }
}
