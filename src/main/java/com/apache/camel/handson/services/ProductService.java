package com.apache.camel.handson.services;

import com.apache.camel.handson.model.Product;
import com.apache.camel.handson.repository.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> fetchProductsByCategory(String category) {
        return productRepo.findByProductCategory(category);
    }
}
