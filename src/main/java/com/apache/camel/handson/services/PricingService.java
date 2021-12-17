package com.apache.camel.handson.services;

import com.apache.camel.handson.model.OrderLine;
import org.springframework.stereotype.Service;

@Service
public class PricingService {

    public OrderLine calculatePrice(final OrderLine orderLine) {
        String productCategory = orderLine.getProduct().getProductCategory();
        if("Electronics".equalsIgnoreCase(productCategory)) {
            orderLine.setPrice(300.00);
        }
		else if("Household".equalsIgnoreCase(productCategory)) {
            orderLine.setPrice(55.0);
        } else {
            orderLine.setPrice(99.0);
        }

        return orderLine;
    }
}
