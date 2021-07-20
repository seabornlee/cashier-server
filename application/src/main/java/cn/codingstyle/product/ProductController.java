package cn.codingstyle.product;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("products")
    public void create(@RequestBody ProductForm productForm) {
        ProductRequest productRequest = productForm.toRequest();
        productService.create(productRequest);
    }

    @PostMapping("/products/{barcode}/scan/{quantity}")
    public ProductReceipt scan(@PathVariable String barcode, @PathVariable Integer quantity) {
        return productService.scan(barcode, quantity);
    }

}
