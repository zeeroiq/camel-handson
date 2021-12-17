package com.apache.camel.handson.repository;

import com.apache.camel.handson.model.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepo extends JpaRepository<OrderLine, String> {


}
