package com.globomart.config;

import com.globomart.controller.PricingController;
import com.globomart.dao.IPricingDao;
import com.globomart.dao.impl.PricingDaoImpl;
import com.globomart.service.IPricingService;
import com.globomart.service.impl.PricingServiceImpl;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

/**
 * Created by kranthi on 6/23/2016.
 */
@Configuration
public class ApplicationConfig {

    @Bean
    public PricingController pricingController(final LoadBalancerClient loadBalancerClient, final IPricingService pricingService) {
        return new PricingController(loadBalancerClient, pricingService);
    }

    @Bean
    public IPricingService pricingService(final IPricingDao pricingDao) {
        return new PricingServiceImpl(pricingDao);
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
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
    public IPricingDao pricingDao(final JdbcTemplate jdbcTemplate) {
        return new PricingDaoImpl(jdbcTemplate);
    }


}
