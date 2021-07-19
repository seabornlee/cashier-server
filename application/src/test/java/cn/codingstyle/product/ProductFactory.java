package cn.codingstyle.product;

import cn.codingstyle.server.application.base.BaseFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductFactory extends BaseFactory {
    private final ProductDAO dao;

    public ProductFactory(ProductDAO dao, JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.dao = dao;
    }

    @Override
    protected String getTableName() {
        return "product";
    }

    public void save(ProductDO ProductDO) {
        dao.save(ProductDO);
    }

}
