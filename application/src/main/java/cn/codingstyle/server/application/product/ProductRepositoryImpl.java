package cn.codingstyle.server.application.product;

import cn.codingstyle.product.Product;
import cn.codingstyle.product.ProductRepository;
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
}
