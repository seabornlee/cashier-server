package cn.codingstyle.product;

public interface ProductRepository {
    void save(Product product);

    Product findByBarcode(String barcode);
}
