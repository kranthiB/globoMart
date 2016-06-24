package com.globomart.controller;

import com.globomart.service.IProductService;
import com.globomart.dto.ProductDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by kranthi on 6/23/2016.
 */
@RestController
@RequestMapping(value = "products")
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addProduct(@RequestBody final ProductDto productVo) {
        productService.addProduct(productVo);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDto> getProductsByTypeAndName(@RequestParam(value = "type", required = false) final String type, @RequestParam(value = "name", required = false) final String name) {
        return productService.getProductsByTypeAndName(type, name);
    }

    @RequestMapping(value = "/searchByType", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDto> getProductsByTypeAndName(@RequestParam(value = "type", required = false) final String type) {
        return productService.getProductsByType(type);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDto> getProducts() {
        return productService.getProducts();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteProduct(@PathVariable final Long id) {
        productService.deleteProduct(id);
    }
}
