package com.apache.camel.handson.bootstrap;

import com.apache.camel.handson.model.Product;
import com.apache.camel.handson.repository.ProductRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class ProductLoader implements CommandLineRunner {

    private final ProductRepo productRepo;

    public ProductLoader(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        if (productRepo.count() == 0) {
            loadProducts();
        }
    }

    private void loadProducts() {
        productRepo.save(Product.builder()
                .productId(UUID.randomUUID().toString())
                .productName("Samsung Galaxy S10")
                .productCategory("mobile")
                .build());
        productRepo.save(Product.builder()
                .productId(UUID.randomUUID().toString())
                .productName("iPhone X")
                .productCategory("mobile")
                .build());
        productRepo.save(Product.builder()
                .productId(UUID.randomUUID().toString())
                .productName("iPhone XI")
                .productCategory("mobile")
                .build());
        productRepo.save(Product.builder()
                .productId(UUID.randomUUID().toString())
                .productName("iPhone XI Pro Max")
                .productCategory("mobile")
                .build());
        productRepo.save(Product.builder()
                .productId(UUID.randomUUID().toString())
                .productName("Oppo")
                .productCategory("mobile")
                .build());

        log.debug(">>>>> No of products available: " + productRepo.count());
    }
}
