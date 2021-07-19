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

    public ProductReceipt scan(String barcode) {
        Product product = productRepository.findByBarcode(barcode);
        ProductItem productItem = new ProductItem(product, 1);
        scanProductItemRepository.add(productItem);
        return new ProductReceipt(scanProductItemRepository.findAll());
//        return new Gson().fromJson("{\"totalAmount\":51.90,\"products\":[{\"name\":\"pizza\",\"quantity\":1,\"unit\":\"\",\"price\":15.00},{\"name\":\"milk\",\"quantity\":3,\"unit\":\"L\",\"price\":12.30}]}", ProductReceipt.class);
    }
}
