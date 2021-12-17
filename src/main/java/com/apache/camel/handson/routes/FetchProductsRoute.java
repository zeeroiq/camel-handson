package com.apache.camel.handson.routes;

import com.apache.camel.handson.services.ProductService;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FetchProductsRoute extends RouteBuilder {

    private static final String URI = "direct:fetchProducts";
    private static final String ROUTE_ID = "direct-fetch-products";

    @Override
    public void configure() throws Exception {
        from(URI)
                .routeId(ROUTE_ID)
                .tracing()
                .log(">>>>> ${body}")
                .bean(ProductService.class, "fetchProductsByCategory")
                .end();
    }
}
