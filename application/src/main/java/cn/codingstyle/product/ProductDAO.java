package cn.codingstyle.product;

import org.springframework.data.repository.CrudRepository;

public interface ProductDAO extends CrudRepository<ProductDO, Integer> {
    ProductDO findByBarcode(String barcode);
}
