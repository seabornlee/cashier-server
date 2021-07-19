package cn.codingstyle.product;

import com.google.gson.Gson;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;

class ProductServiceTest {

    private ProductRepository productRepository;
    private ScanProductItemRepository scanProductItemRepository;
    private ProductService productService;

    @BeforeEach
    void setUp() {
        productRepository = Mockito.mock(ProductRepository.class);
        scanProductItemRepository = new ScanProductItemRepositoryDouble();
        productService = new ProductService(productRepository, scanProductItemRepository);
    }

    @Test
    void scan_one_product_first_time() throws JSONException {
        when(productRepository.findByBarcode("12345678")).thenReturn(givenProduct());
        ProductReceipt result = productService.scan("12345678");
        String expected = "{\"totalAmount\":15.00,\"products\":[{\"name\":\"pizza\",\"quantity\":1,\"unit\":\"\",\"price\":15.00}]}";
        JSONAssert.assertEquals(expected, new Gson().toJson(result), false);
    }

    @Test
    void scan_one_product_when_has_scan_one_product() throws JSONException {
        when(productRepository.findByBarcode("12345678")).thenReturn(givenProduct());
        ProductItem productItem = new ProductItem("milk", "L", 3, new BigDecimal("12.30"));
        scanProductItemRepository.add(productItem);
        ProductReceipt result = productService.scan("12345678");
        String expected = "{\"totalAmount\":51.90,\"products\":[{\"name\":\"pizza\",\"quantity\":1,\"unit\":\"\",\"price\":15.00},{\"name\":\"milk\",\"quantity\":3,\"unit\":\"L\",\"price\":12.30}]}";
        JSONAssert.assertEquals(expected, new Gson().toJson(result), false);
    }

    private Product givenProduct() {
        return Product.builder()
                .barcode("12345678")
                .name("pizza")
                .unit("")
                .price(new BigDecimal("15.00"))
                .type("1")
                .build();
    }
}
