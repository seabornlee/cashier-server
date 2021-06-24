package cn.codingstyle.server.application.item;

import cn.codingstyle.server.application.base.AutoClear;
import cn.codingstyle.server.application.base.RestCall;
import cn.codingstyle.server.application.item.repository.ItemDO;
import cn.codingstyle.server.item.Item;
import com.google.gson.Gson;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ItemTestSteps {
    @Autowired
    RestCall restCall;
    @Autowired
    AutoClear autoClear;
    @Autowired
    ItemFactory itemFactory;

    private String response;


    @Before
    public void setUp() {
        autoClear.clear(this);
    }

    @When("新增商品信息")
    public void 新增商品信息(String content) throws IOException {
        restCall.post("/items", content);
    }

    @Then("有商品信息")
    public void 有商品信息(List<Map<String, String>> items) {
        List<ItemDO> itemDOs = itemFactory.all();
        assertThat(itemDOs.size(), is(items.size()));
        List<Map<String, String>> actual = itemDOs.stream().map(itemDO -> {
            Map<String, String> value = new HashMap<>();
            value.put("条码", itemDO.getBarcode());
            value.put("名称", itemDO.getName());
            value.put("单位", itemDO.getUnit());
            value.put("价格", itemDO.getPrice().toString());
            value.put("类型", itemDO.getType());
            return value;
        }).collect(Collectors.toList());
        assertThat(actual, is(items));
    }

    @Given("已存在商品信息")
    public void 已存在商品信息(List<Map<String, String>> items) {
        items.forEach(item ->
                {
                    ItemDO itemDO = ItemDO.builder()
                            .barcode(item.get("条码"))
                            .name(item.get("名称"))
                            .unit(item.get("单位"))
                            .price(new BigDecimal(item.get("价格")))
                            .type(item.get("类型"))
                            .build();
                    itemFactory.save(itemDO);
                }
        );
    }

    @When("根据条码 {string} 查询商品")
    public void 根据条码查询商品(String barcode) throws IOException {
        response = restCall.get("/items/" + barcode);
    }

    @Then("得到商品信息")
    public void 得到商品信息(List<Map<String, String>> expected) {
        Item item = new Gson().fromJson(response, Item.class);
        Map<String, String> value = new HashMap<>();
        value.put("条码", item.getBarcode());
        value.put("名称", item.getName());
        value.put("单位", item.getUnit());
        value.put("价格", item.getPrice().toString());
        value.put("类型", item.getType());

        List<Map<String, String>> actual = Arrays.asList(value);
        assertThat(actual, is(expected));
    }

    @When("更新商品{string}信息")
    public void 更新商品信息(String id, String content) throws IOException {
        response = restCall.put("/items/" + id, content);
    }

    @When("根据id {string} 删除商品")
    public void 根据id删除商品(String id) throws IOException {
        restCall.delete("/items/" + id);
    }

    @Then("id 为 {string} 的商品不存在")
    public void id为的商品不存在(String id) {
        assertThat(itemFactory.findById(Integer.valueOf(id)).isPresent(), is(false));
    }
}