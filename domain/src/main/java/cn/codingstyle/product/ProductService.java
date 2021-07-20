package cn.codingstyle.product;

public class ProductService {
    private final ProductRepository productRepository;
    private final ScanProductItemRepository scanProductItemRepository;

    public ProductService(ProductRepository productRepository, ScanProductItemRepository scanProductItemRepository) {
        this.productRepository = productRepository;
        this.scanProductItemRepository = scanProductItemRepository;
    }

    public void create(ProductRequest productRequest) {
        Product product = productRequest.toEntity();
        productRepository.save(product);
    }

    public ProductReceipt scan(String barcode, Integer quantity) {
        Product product = productRepository.findByBarcode(barcode);
        ProductItem productItem = new ProductItem(product, quantity);
        scanProductItemRepository.add(productItem);
        return new ProductReceipt(scanProductItemRepository.findAll());
    }
}
