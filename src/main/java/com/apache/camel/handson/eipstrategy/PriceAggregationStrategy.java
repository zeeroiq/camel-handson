package com.apache.camel.handson.eipstrategy;

import com.apache.camel.handson.model.OrderDTO;
import com.apache.camel.handson.model.OrderLine;
import com.apache.camel.handson.repository.OrderLineRepo;
import com.apache.camel.handson.repository.OrderRepo;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Component
public class PriceAggregationStrategy implements AggregationStrategy {
    private final OrderRepo orderRepo;
    private final OrderLineRepo orderLineRepo;

    public PriceAggregationStrategy(OrderRepo orderRepo, OrderLineRepo orderLineRepo) {
        this.orderRepo = orderRepo;
        this.orderLineRepo = orderLineRepo;
    }

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        OrderLine body = newExchange.getIn().getBody(OrderLine.class);
        if(oldExchange == null) {
            orderLineRepo.findById()
            OrderDTO order = OrderDTO.builder()
                    .orderNo(UUID.randomUUID().toString())
                    .orderDate(Instant.now().toString())
                    .orderPrice(body.getPrice())
                    .build();
            order.addOrderLines(body);
            newExchange.getIn().setBody(order, OrderDTO.class);
            return newExchange;
        }
        OrderLine newOrderLine = newExchange.getIn().getBody(OrderLine.class);
        OrderDTO order = oldExchange.getIn().getBody(OrderDTO.class);
        order.setOrderPrice((order.getOrderPrice() == null ? 0.0 : order.getOrderPrice()) + newOrderLine.getPrice());
        order.addOrderLines(newOrderLine);
        log.info(">>>>> " + order);
        oldExchange.getIn().setBody(order);
        return oldExchange;
    }
}
