package com.apache.camel.handson.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderLine {

    private Product product;
    private Integer nUnits;
    private Double price;

}
