package com.globomart.service.impl;

import com.globomart.dao.IPricingDao;
import com.globomart.dto.Price;
import com.globomart.dto.ProductDto;
import com.globomart.service.IPricingService;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by kranthi on 6/23/2016.
 */
public class PricingServiceImpl implements IPricingService {

    public static final String NO_PRODUCT_FOUND = "No Product Found";
    private final IPricingDao pricingDao;

    public PricingServiceImpl(IPricingDao pricingDao) {
        this.pricingDao = pricingDao;
    }

    @Override
    public String getPrice(final List<ProductDto> productVos) {
        if (CollectionUtils.isEmpty(productVos)) {
            return NO_PRODUCT_FOUND;
        }
        ProductDto productVo = productVos.get(0);
        final Price price = pricingDao.findByProductId(productVo.getId());
        return price == null ? NO_PRODUCT_FOUND : price.getPrice() == null ? NO_PRODUCT_FOUND : price.getPrice().toString();
    }
}
