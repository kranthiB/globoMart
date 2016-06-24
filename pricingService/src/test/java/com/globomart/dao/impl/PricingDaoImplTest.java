package com.globomart.dao.impl;

import com.globomart.dto.Price;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * Created by kranthi on 23-06-2016.
 */
public class PricingDaoImplTest {

    private EmbeddedDatabase db;

    @Before
    public void setUp() {
        db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("create-db.sql")
                .addScript("insert-data.sql")
                .build();
    }

    @Test
    public void testFindByProductId() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(db);
        PricingDaoImpl pricingDao = new PricingDaoImpl(jdbcTemplate);
        Price price = pricingDao.findByProductId(2L);
        Assert.assertNotNull(price);
    }
}
