package cn.codingstyle.server.application.controller;

import cn.codingstyle.Application;
import cn.codingstyle.product.*;
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
    void should_get_product_list_when_scan_code() throws Exception {
        givenProductItem();
        givenProduct();
        String result = mockMvc.perform(post("/products/12345678/scan"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String expected = "{\"totalAmount\":51.90,\"products\":[{\"name\":\"pizza\",\"quantity\":1,\"unit\":\"\",\"price\":15.00},{\"name\":\"milk\",\"quantity\":3,\"unit\":\"L\",\"price\":12.30}]}";
        JSONAssert.assertEquals(expected, result, false);

    }

    private void givenProductItem() {
        ProductItem productItem = new ProductItem("milk", "L", 3, new BigDecimal("12.30"));
        scanProductItemRepository.add(productItem);
    }

    private ProductDO givenProduct() {
        ProductDO itemDO = ProductDO.builder()
                .barcode("12345678")
                .name("pizza")
                .unit("")
                .price(new BigDecimal("15.00"))
                .type("1")
                .build();
        productFactory.save(itemDO);
        return itemDO;
    }

}
