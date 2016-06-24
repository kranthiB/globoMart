package com.globomart.service.impl;

import com.globomart.dao.IProductDao;
import com.globomart.dto.ProductDto;
import com.globomart.service.IProductService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kranthi on 6/23/2016.
 */
public class ProductServiceImpl implements IProductService {

    private final IProductDao productDao;

    public ProductServiceImpl(IProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public void addProduct(ProductDto productVo) {
        final ProductDto ProductDto = new ProductDto();
        ProductDto.setName(productVo.getName());
        ProductDto.setType(productVo.getType());
        productDao.save(ProductDto);
    }

    @Override
    public List<ProductDto> getProducts() {
        return productDao.findAll();
    }

    @Override
    public void deleteProduct(Long id) {
        productDao.delete(id);
    }

    @Override
    public List<ProductDto> getProductsByTypeAndName(String type, String name) {
        return productDao.findByTypeAndName(type, name);
    }

    @Override
    public List<ProductDto> getProductsByType(String type) {
        return productDao.findByType(type);
    }
}
