package com.apache.camel.handson.routes;

import com.apache.camel.handson.eipstrategy.PriceAggregationStrategy;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class OrderProcessingRoute extends RouteBuilder {

    private final String URI = "direct:processOrders";

    private final PriceAggregationStrategy priceAggregationStrategy;

    public OrderProcessingRoute(PriceAggregationStrategy priceAggregationStrategy) {
        this.priceAggregationStrategy = priceAggregationStrategy;
    }

    @Override
    public void configure() throws Exception {
        from(URI)
                .split(body(), priceAggregationStrategy).parallelProcessing()
                .to("bean:pricingService?method=calculatePrice")
                .end();
    }
}
