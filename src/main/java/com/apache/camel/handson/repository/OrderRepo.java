package com.apache.camel.handson.repository;

import com.apache.camel.handson.model.OrderDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<OrderDTO, String> {
}
