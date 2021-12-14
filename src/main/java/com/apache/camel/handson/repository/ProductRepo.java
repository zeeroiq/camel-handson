package com.apache.camel.handson.repository;

import com.apache.camel.handson.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, String> {
    List<Product> findByProductCategory(String productCategory);
}
