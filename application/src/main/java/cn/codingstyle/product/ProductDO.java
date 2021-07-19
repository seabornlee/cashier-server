package cn.codingstyle.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String barcode;
    private String name;
    private String unit;
    private BigDecimal price;
    private String type;

    public static ProductDO from(Product product) {
        return builder()
                .id(product.getId())
                .name(product.getName())
                .barcode(product.getBarcode())
                .price(product.getPrice())
                .type(product.getType())
                .unit(product.getUnit())
                .build();
    }

    public Product toEntity() {
        return Product.builder()
                .id(id)
                .name(name)
                .barcode(barcode)
                .price(price)
                .type(type)
                .unit(unit)
                .build();
    }
}
