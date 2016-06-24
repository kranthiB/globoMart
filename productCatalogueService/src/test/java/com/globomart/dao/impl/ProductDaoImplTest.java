package com.globomart.dao.impl;

import com.globomart.dto.ProductDto;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.util.List;

/**
 * Created by kranthi on 23-06-2016.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductDaoImplTest {
    private static EmbeddedDatabase db;
    private static ProductDaoImpl productDao;
    private static JdbcTemplate jdbcTemplate;

    @BeforeClass
    public static void setUp() {
        db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("create-db.sql")
                .addScript("insert-data.sql")
                .build();
        jdbcTemplate = new JdbcTemplate(db);
        productDao = new ProductDaoImpl(jdbcTemplate);
    }

    @Test
    public void test3FindByTypeAndName() {
        List<ProductDto> productDtos = productDao.findByTypeAndName("tv", "Sony");
        Assert.assertTrue(productDtos.size() != 4);
    }

    @Test
    public void test2FindByType() {
        List<ProductDto> productDtos = productDao.findByType("car");
        Assert.assertTrue(productDtos.size() != 4);
    }

    @Test
    public void test1FindAll() {
        List<ProductDto> productDtos = productDao.findAll();
        Assert.assertTrue(productDtos.size() == 4);

    }

    @Test
    public void test4Delete() {
        productDao.delete(1L);
        List<ProductDto> productDtos = productDao.findAll();
        Assert.assertTrue(productDtos.size() == 3);
    }

    @Test
    public void test5Save() {
        ProductDto productDto = new ProductDto();
        productDto.setId(5L);
        productDto.setType("car");
        productDto.setName("SWIFT");
        productDao.save(productDto);
        List<ProductDto> productDtos = productDao.findAll();
        Assert.assertTrue(productDtos.size() == 4);

    }
}
