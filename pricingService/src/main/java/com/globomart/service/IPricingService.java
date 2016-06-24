package com.globomart.service;

import com.globomart.dto.ProductDto;

import java.util.List;

/**
 * Created by kranthi on 6/23/2016.
 */
public interface IPricingService {

    String getPrice(List<ProductDto> productVos);
}
