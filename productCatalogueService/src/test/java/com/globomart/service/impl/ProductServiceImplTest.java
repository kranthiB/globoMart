package com.globomart.service.impl;

import com.globomart.dao.IProductDao;
import com.globomart.dto.ProductDto;
import com.globomart.service.IProductService;
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
public class ProductServiceImplTest {

    @Mock
    private IProductDao productDao;
    private IProductService productService;

    private List<ProductDto> productDtos;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        productService = new ProductServiceImpl(productDao);
        productDtos = new ArrayList<>();
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setName("BMW");
        productDto.setType("car");
        productDtos.add(productDto);
        productDto = new ProductDto();
        productDto.setId(2L);
        productDto.setName("NANO");
        productDto.setType("car");
        productDtos.add(productDto);
    }

    @Test
    public void testGetProducts() {
        Mockito.doReturn(productDtos).when(productDao).findAll();
        List<ProductDto> productDtoList = productService.getProducts();
        Assert.assertTrue(productDtoList.size() == productDtos.size());
    }

    @Test
    public void testDeleteProduct() {
        Mockito.doNothing().when(productDao).delete(Mockito.anyLong());
        long id = 1L;
        productService.deleteProduct(id);
        Mockito.verify(productDao).delete(id);
    }

    @Test
    public void testGetProductsByTypeAndName() {
        Mockito.doReturn(productDtos).when(productDao).findByTypeAndName(Mockito.anyString(), Mockito.anyString());
        List<ProductDto> productDtoList = productService.getProductsByTypeAndName("car", "BMW");
        Assert.assertTrue(productDtoList.size() == productDtos.size());
    }

    @Test
    public void testGetProductsByType() {
        Mockito.doReturn(productDtos).when(productDao).findByType(Mockito.anyString());
        List<ProductDto> productDtoList = productService.getProductsByType("car");
        Assert.assertTrue(productDtoList.size() == productDtos.size());
    }
}
