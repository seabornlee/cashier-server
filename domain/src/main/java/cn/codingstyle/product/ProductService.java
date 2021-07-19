package cn.codingstyle.product;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void create(ProductRequest productRequest) {
        Product product = productRequest.toEntity();
        productRepository.save(product);
    }
}
