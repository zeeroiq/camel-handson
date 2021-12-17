package com.apache.camel.handson.services;

import com.apache.camel.handson.model.OrderDTO;
import com.apache.camel.handson.model.OrderLine;
import com.apache.camel.handson.repository.OrderLineRepo;
import com.apache.camel.handson.repository.OrderRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepo orderRepo;
    private final OrderLineRepo orderLineRepo;

    public OrderService(OrderRepo orderRepo, OrderLineRepo orderLineRepo) {
        this.orderRepo = orderRepo;
        this.orderLineRepo = orderLineRepo;
    }

    public List<OrderDTO> fetchOrders(){
        return orderRepo.findAll();
    }

    public List<OrderLine> fetchOrderLine() {
        return orderLineRepo.findAll();
    }
}
