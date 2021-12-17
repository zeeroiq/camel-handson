package com.apache.camel.handson.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.criterion.Order;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO implements Serializable {
    private static final long serialVersionUID = -2034560810857484418L;
    @Id
    private String orderNo;
    private String orderDate;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderLine> orderLines;
    private Double totalDiscount;
    private Double orderPrice;

    public void addOrderLines(OrderLine orderLine) {
        if(orderLines == null) {
            orderLines = new ArrayList<>();
        }
        orderLines.add(orderLine);
    }

}
