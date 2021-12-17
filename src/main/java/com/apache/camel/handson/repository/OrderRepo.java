package com.apache.camel.handson.repository;

import com.apache.camel.handson.model.OrderDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepo extends JpaRepository<OrderDTO, String> {

    @Query(value = "SELECT order_no FROM order_to_orderline WHERE orderline_id=:orderLineId", nativeQuery = true)
    String findOrderNoFromOrderToOrderLine(String orderLineId);
}
