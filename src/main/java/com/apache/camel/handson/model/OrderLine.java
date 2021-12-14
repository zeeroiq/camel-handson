package com.apache.camel.handson.model;

import lombok.Data;

@Data
public class OrderLine {

    private Product product;
    private Integer nUnits;
    private Double price;

}
