package cn.codingstyle.server.application.product;

import org.springframework.data.repository.CrudRepository;

public interface ProductDAO extends CrudRepository<ProductDO, Integer> {
}
