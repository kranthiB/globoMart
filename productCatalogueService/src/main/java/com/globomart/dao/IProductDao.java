package com.globomart.dao;


import com.globomart.dto.ProductDto;

import java.util.List;

/**
 * Created by kranthi on 6/23/2016.
 */
public interface IProductDao {

    List<ProductDto> findByTypeAndName(String type, String name);

    List<ProductDto> findByType(String type);

    List<ProductDto> findAll();

    void delete(long id);

    void save(ProductDto ProductDto);

}
