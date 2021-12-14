package com.apache.camel.handson.model;

import lombok.Data;

import java.util.List;

@Data
public class Order {

    private String orderNo;
    private String orderDate;
    private List<OrderLine> orderLines;
    private Double totalDiscount;
    private Double orderPrice;

}
