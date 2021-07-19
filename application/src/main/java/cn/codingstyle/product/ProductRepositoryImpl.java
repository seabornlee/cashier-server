package cn.codingstyle.product;

import org.springframework.stereotype.Component;

@Component
public class ProductRepositoryImpl implements ProductRepository {
    private final ProductDAO productDAO;

    public ProductRepositoryImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public void save(Product product) {
        ProductDO productDO = ProductDO.from(product);
        productDAO.save(productDO);
    }

    @Override
    public Product findByBarcode(String barcode) {
        ProductDO productDO = productDAO.findByBarcode(barcode);
        return productDO.toEntity();
    }

}
