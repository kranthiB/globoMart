package com.globomart.util;

/**
 * Created by kranthi on 6/23/2016.
 */
public enum ServiceName {

    PRODUCT_CATALOGUE_SERVICE("productCatalogueService");

    private String name;

    ServiceName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
