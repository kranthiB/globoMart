package com.globomart.service;

import com.globomart.dto.ProductDto;

import java.util.List;

/**
 * Created by kranthi on 6/23/2016.
 */
public interface IProductService {

    void addProduct(ProductDto productVo);

    List<ProductDto> getProducts();

    void deleteProduct(Long id);

    List<ProductDto> getProductsByTypeAndName(String type, String name);

    List<ProductDto> getProductsByType(String type);
}
