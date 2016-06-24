package com.globomart.config;

import com.globomart.controller.ProductController;
import com.globomart.dao.impl.ProductDaoImpl;
import com.globomart.service.IProductService;
import com.globomart.service.impl.ProductServiceImpl;
import com.globomart.dao.IProductDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * Created by kranthi on 6/23/2016.
 */
@Configuration
public class ApplicationConfig {

    @Bean
    public IProductService productService(final IProductDao IProductDao) {
        return new ProductServiceImpl(IProductDao);
    }

    @Bean
    public ProductController productController(final IProductService productService) {
        return new ProductController(productService);
    }

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase embeddedDatabase = embeddedDatabaseBuilder.setType(EmbeddedDatabaseType.H2)
                .addScript("create-db.sql").addScript("insert-data.sql").build();
        return embeddedDatabase;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(final DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public IProductDao productDao(final JdbcTemplate jdbcTemplate) {
        return new ProductDaoImpl(jdbcTemplate);
    }
}
