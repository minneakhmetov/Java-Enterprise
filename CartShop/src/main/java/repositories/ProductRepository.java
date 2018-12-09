/*
 * Developed by Razil Minneakhmetov on 10/24/18 10:26 PM.
 * Last modified 10/24/18 10:26 PM.
 * Copyright © 2018. All rights reserved.
 */

package repositories;

import forms.LoginForm;
import lombok.SneakyThrows;
import models.Product;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

public class ProductRepository {
    private JdbcTemplate jdbcTemplate;
    private Random random;

    public ProductRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //language=SQL
    private static final String SQL_INSERT =
            "INSERT INTO product (name, price, avatar) values (?, ?, ?)";

    //language=SQL
    private static final String SQL_SELECT =
            "SELECT * from product where id = ?";

    //language=SQL
    private static final String SQL_SELECT_ALL =
            "SELECT * from product";

    //language=SQL
    private static final String SQL_DELETE =
            "DELETE from product";

    private static final String SQL_FIND =
            "select * from product where position(upper(?) in upper(product.name)) > 0;";


    private RowMapper<Product> rowMapper = (resultSet, i) -> Product.builder()
            .avatar(resultSet.getString("avatar"))
            .id(resultSet.getLong("id"))
            .name(resultSet.getString("name"))
            .price(resultSet.getString("price"))
            .build();


    public void create(Product product){
        jdbcTemplate.update(SQL_INSERT, product.getName(), product.getPrice(), product.getAvatar());
    }

    public void delete(){
        jdbcTemplate.update(SQL_DELETE);
    }

    public Product readOne(Long id){
        return jdbcTemplate.queryForObject(SQL_SELECT, rowMapper, id);
    }

    public List<Product> readAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, rowMapper);
    }

    @SneakyThrows
    public void batchUpdate(final List<LoginForm> users) {
        random = new Random(System.currentTimeMillis());
        jdbcTemplate.batchUpdate(SQL_INSERT,
                new BatchPreparedStatementSetter() {
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, users.get(i).getName());
                        ps.setInt(2, random.nextInt(95) + 5);
                        ps.setString(3, users.get(i).getPhotoURL());
                    }

                    public int getBatchSize() {
                        return users.size();
                    }
                } );

    }

    public List<Product> find(String string){
        return jdbcTemplate.query(SQL_FIND, rowMapper, string);
    }







}