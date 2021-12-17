package com.apache.camel.handson.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderLine implements Serializable {
    private static final long serialVersionUID = 968791968777495104L;

    @Id
    private String orderLineId;
    @ManyToOne(optional = false)
    @JoinColumn(name = "product_product_id", nullable = false)
    private Product product;
    private Integer nUnits;
    private Double price;

}
