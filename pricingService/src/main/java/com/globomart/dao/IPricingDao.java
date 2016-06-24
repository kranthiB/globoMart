package com.globomart.dao;

import com.globomart.dto.Price;

/**
 * Created by kranthi on 6/23/2016.
 */
public interface IPricingDao {
    Price findByProductId(Long id);
}
