package com.apache.camel.handson.bootstrap;

import com.apache.camel.handson.model.OrderDTO;
import com.apache.camel.handson.model.OrderLine;
import com.apache.camel.handson.model.Product;
import com.apache.camel.handson.repository.OrderRepo;
import com.apache.camel.handson.repository.ProductRepo;
import com.apache.camel.handson.services.PricingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class BootstrapApp implements CommandLineRunner {

    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;
    private final PricingService pricingService;

    public BootstrapApp(ProductRepo productRepo, OrderRepo orderRepo, PricingService pricingService) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
        this.pricingService = pricingService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (productRepo.count() == 0) {
            loadProducts();
        }
        if (orderRepo.count() == 0) {
            loadOrders();
        }
    }

    private List<OrderLine> getOrderLines() {
        List<OrderLine> orderLines = new ArrayList<>();
        getProducts().stream().
                forEach(product -> {
                    OrderLine orderLine = OrderLine.builder()
                            .orderlineId(UUID.randomUUID().toString())
                            .product(product)
                            .nUnits(1)
                            .build();
//                    orderLine = pricingService.calculatePrice(orderLine);
                    orderLines.add(orderLine);

                });
        return orderLines;
    }

    private void loadOrders() {
        getOrderLines().stream().forEach(orderLine -> orderRepo.save(OrderDTO.builder()
                .orderNo(UUID.randomUUID().toString())
                .orderDate(Instant.now().toString())
                .orderPrice(orderLine.getPrice())
                .orderLines(List.of(orderLine))
                .totalDiscount(0.0)
                .build()));
//        orderRepo.save(OrderDTO.builder()
//
//                .orderLines(getOrderLines())
//                .build());
    }

    private void loadProducts() {
        productRepo.save(Product.builder()
                .productId(UUID.randomUUID().toString())
                .productName("Samsung Galaxy S10")
                .productCategory("electronics")
                .build());
        productRepo.save(Product.builder()
                .productId(UUID.randomUUID().toString())
                .productName("iPhone X")
                .productCategory("electronics")
                .build());
        productRepo.save(Product.builder()
                .productId(UUID.randomUUID().toString())
                .productName("iPhone XI")
                .productCategory("electronics")
                .build());
        productRepo.save(Product.builder()
                .productId(UUID.randomUUID().toString())
                .productName("iPhone XI Pro Max")
                .productCategory("electronics")
                .build());
        productRepo.save(Product.builder()
                .productId(UUID.randomUUID().toString())
                .productName("Oppo")
                .productCategory("electronics")
                .build());
        productRepo.save(Product.builder()
                .productId(UUID.randomUUID().toString())
                .productName("Cutlery")
                .productCategory("household")
                .build());
        productRepo.save(Product.builder()
                .productId(UUID.randomUUID().toString())
                .productName("Bed")
                .productCategory("household")
                .build());
        productRepo.save(Product.builder()
                .productId(UUID.randomUUID().toString())
                .productName("Matres")
                .productCategory("household")
                .build());

        log.debug(">>>>> No of products available: " + productRepo.count());
    }

    private List<Product> getProducts() {
        return productRepo.findAll();
    }
}
