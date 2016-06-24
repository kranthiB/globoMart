package com.globomart.dao.impl;

import com.globomart.dao.IPricingDao;
import com.globomart.dto.Price;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by kranthi on 23-06-2016.
 */
public class PricingDaoImpl implements IPricingDao {

    private JdbcTemplate jdbcTemplate;

    public PricingDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Price findByProductId(Long id) {
        return jdbcTemplate.queryForObject("select * from price where productId = ?",
                new RowMapper<Price>() {
                    @Override
                    public Price mapRow(ResultSet resultSet, int i) throws SQLException {
                        Price price = new Price();
                        price.setId(resultSet.getLong("id"));
                        price.setPrice(resultSet.getDouble("price"));
                        price.setProductId(resultSet.getLong("productId"));
                        return price;
                    }
                }, id);
    }
}
