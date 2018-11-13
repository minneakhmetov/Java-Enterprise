/*
 * Developed by Razil Minneakhmetov on 10/25/18 7:33 PM.
 * Last modified 10/25/18 7:33 PM.
 * Copyright © 2018. All rights reserved.
 */

package repositories;

import models.Cart;
import models.Product;
import models.ProductWithUrl;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

public class CartRepository {

    private JdbcTemplate jdbcTemplate;

    public CartRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<ProductWithUrl> rowMapperURL = (resultSet, i) -> ProductWithUrl.builder()
            .avatar(resultSet.getLong("avatar"))
            .id(resultSet.getLong("id"))
            .name(resultSet.getString("name"))
            .price(resultSet.getString("price"))
            .URL(resultSet.getString("URL"))
            .build();

    private static final String SQL_INSERT =
            "INSERT INTO cart (user_id, product_id) VALUES (?, ?)";

    private static final String SQL_SELECT =
            "SELECT * FROM cart join product on cart.product_id = product.id where product_id = ?";

    public void create(Cart cart){
        jdbcTemplate.update(SQL_INSERT, cart.getUserId(), cart.getProductId());
    }

    public List<ProductWithUrl> readProductsByUser(Long userId){
        return jdbcTemplate.query(SQL_SELECT, rowMapperURL, userId);
    }

}