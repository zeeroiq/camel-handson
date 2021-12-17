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
import java.util.Optional;
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
//            orderLineRepo.findById()
            String orderNo = orderRepo.findOrderNoFromOrderToOrderLine(body.getOrderlineId());
            OrderDTO order = orderRepo.findById(orderNo).orElse(OrderDTO.builder()
                    .orderNo("invalid-order")
                    .orderDate(Instant.now().toString())
                    .orderPrice(0.0)
                    .build());
            order.setOrderPrice(body.getPrice());
            newExchange.getIn().setBody(order, OrderDTO.class);
            return newExchange;
        }
        OrderLine newOrderLine = newExchange.getIn().getBody(OrderLine.class);
        OrderDTO order = oldExchange.getIn().getBody(OrderDTO.class);
        Double totalPrice = order.getOrderLines().stream()
                .map(orderLine -> orderLine.getPrice())
                .reduce(0.0, (price1, price2) -> price1 + (price2 == null ? 0.0 : price2));

        order.setOrderPrice(totalPrice);
        log.info(">>>>> newOrderLine" + newOrderLine);
        log.info(">>>>> OrderDTO" + order);
        oldExchange.getIn().setBody(order);
        return oldExchange;
    }
}
