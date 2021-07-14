package cn.codingstyle.server.application.controller;

import cn.codingstyle.server.application.Application;
import cn.codingstyle.server.application.item.ItemFactory;
import cn.codingstyle.server.application.item.ItemForm;
import cn.codingstyle.server.application.item.repository.ItemDO;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = Application.class)
@Transactional(rollbackFor = TransactionException.class)
public class ItemControllerTest {
    MockMvc mockMvc;
    @Autowired
    private ItemFactory itemFactory;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() {
        itemFactory.clear();
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void add_item_success() throws Exception {
        ItemForm itemForm = ItemForm.builder()
                .barcode("12345678")
                .name("juice")
                .price(BigDecimal.valueOf(13.60))
                .type("1")
                .unit("L").build();

        mockMvc.perform(post("/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(itemForm)))
                .andExpect(status().isOk());

        List<ItemDO> all = itemFactory.all();
        assertThat(all.size()).isEqualTo(1);
        ItemDO itemDO = all.get(0);
        JSONAssert.assertEquals(new Gson().toJson(itemForm), new Gson().toJson(itemDO), false);
    }

    @Test
    void delete_item_success() throws Exception {
        ItemDO itemDO = givenItem();
        mockMvc.perform(delete("/items/{id}", itemDO.getId()))
                .andExpect(status().isOk());
        assertThat(this.itemFactory.findById(itemDO.getId()).isPresent()).isFalse();
    }

    @Test
    void update_item_success() throws Exception {
        ItemDO itemDO = givenItem();

        ItemForm itemForm = ItemForm.builder()
                .barcode("12345678")
                .name("juice")
                .price(BigDecimal.valueOf(13.60))
                .type("1")
                .unit("L").build();

        mockMvc.perform(put("/items/{id}", itemDO.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(itemForm)))
                .andExpect(status().isOk());

        List<ItemDO> all = itemFactory.all();
        assertThat(all.size()).isEqualTo(1);
        ItemDO updatedItemDO = all.get(0);
        JSONAssert.assertEquals(new Gson().toJson(itemForm), new Gson().toJson(updatedItemDO), false);
    }

    @Test
    void get_item_success() throws Exception {
        ItemDO itemDO = givenItem();
        String result = mockMvc.perform(get("/items/{barcode}", itemDO.getBarcode()))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        JSONAssert.assertEquals(new Gson().toJson(itemDO), result, false);
    }

    private ItemDO givenItem() {
        ItemDO itemDO = ItemDO.builder()
                .barcode("82203002")
                .name("rice")
                .unit("KG")
                .price(new BigDecimal("7.09"))
                .type("1")
                .build();
        itemFactory.save(itemDO);
        return itemDO;
    }

}
