package com.apache.camel.handson.controller;

import com.apache.camel.handson.model.Product;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProducerTemplate producerTemplate;

    @GetMapping(value = "/products/{category}", produces = "application/json")
    public List<Product> getProductsByCategory(
            @PathVariable("category") final String category) {
        producerTemplate.start();
        List<Product> products = producerTemplate
                .requestBody("direct:fetchProducts", category, List.class);

        producerTemplate.stop();
        return products;

    }
}
