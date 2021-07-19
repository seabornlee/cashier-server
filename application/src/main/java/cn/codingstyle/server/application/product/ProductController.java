package cn.codingstyle.server.application.product;

import cn.codingstyle.product.ProductRequest;
import cn.codingstyle.product.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public void create(@RequestBody ProductForm productForm) {
        ProductRequest productRequest = productForm.toRequest();
        productService.create(productRequest);
    }
}
