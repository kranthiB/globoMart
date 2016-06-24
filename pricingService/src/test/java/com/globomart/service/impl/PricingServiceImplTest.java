package com.globomart.service.impl;

import com.globomart.dao.IPricingDao;
import com.globomart.dto.Price;
import com.globomart.dto.ProductDto;
import com.globomart.service.IPricingService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kranthi on 23-06-2016.
 */
public class PricingServiceImplTest {

    @Mock
    private IPricingDao pricingDao;
    private IPricingService pricingService;
    private List<ProductDto> productDtos;
    private Price price;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        pricingService = new PricingServiceImpl(pricingDao);
        productDtos = new ArrayList<>();
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setName("BMW");
        productDto.setType("car");
        productDtos.add(productDto);
        price = new Price();
        price.setId(1L);
        price.setProductId(1L);
        price.setPrice(220.3D);
    }

    @Test
    public void testGetPrice() {
        Mockito.doReturn(price).when(pricingDao).findByProductId(Mockito.anyLong());
        String output = pricingService.getPrice(productDtos);
        Assert.assertTrue(output.equalsIgnoreCase(price.getPrice().toString()));
    }

    @Test
    public void testGetPriceForNoProductFound() {
        String output = pricingService.getPrice(null);
        Assert.assertTrue(output.equalsIgnoreCase(PricingServiceImpl.NO_PRODUCT_FOUND));
    }

}
