package cn.codingstyle.server.application.controller;

import cn.codingstyle.Application;
import cn.codingstyle.product.ProductDO;
import cn.codingstyle.product.ProductFactory;
import cn.codingstyle.product.ScanProductItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = Application.class)
@Transactional(rollbackFor = TransactionException.class)
public class ProductControllerTest {
    MockMvc mockMvc;
    @Autowired
    private ProductFactory productFactory;
    @Autowired
    private ScanProductItemRepository scanProductItemRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() {
        productFactory.clear();
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void should_return_receipt_when_scan_barcode() throws Exception {
        ProductDO pizza = givenProduct("12345678", "pizza", "piece", "15.00");
        ProductDO milk = givenProduct("22345678", "milk", "L", "12.30");
        String result = scan(pizza.getBarcode());
        JSONAssert.assertEquals("{\"totalAmount\":15,\"products\":[{\"name\":\"pizza\",\"quantity\":1,\"unit\":\"piece\",\"price\":15}]}", result, false);

        String secondResult = scan(pizza.getBarcode());
        JSONAssert.assertEquals("{\"totalAmount\":30,\"products\":[{\"name\":\"pizza\",\"quantity\":2,\"unit\":\"piece\",\"price\":15}]}", secondResult, false);

        String milkScanResult = scan(milk.getBarcode(), 2);
        JSONAssert.assertEquals("{\"totalAmount\":54.6,\"products\":[{\"name\":\"pizza\",\"quantity\":2,\"unit\":\"piece\",\"price\":15},{\"name\":\"milk\",\"quantity\":2,\"unit\":\"L\",\"price\":12.3}]}", milkScanResult, false);
    }

    private String scan(String barcode) throws Exception {
        return scan(barcode, 1);
    }

    private String scan(String barcode, int quantity) throws Exception {
        return mockMvc.perform(post("/products/{barcode}/scan/{quantity}", barcode, quantity))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    private ProductDO givenProduct(String barcode, String name, String unit, String price) {
        ProductDO itemDO = ProductDO.builder()
                .barcode(barcode)
                .name(name)
                .unit(unit)
                .price(new BigDecimal(price))
                .type("1")
                .build();
        productFactory.save(itemDO);
        return itemDO;
    }

}
