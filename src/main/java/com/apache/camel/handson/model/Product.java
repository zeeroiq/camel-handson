package com.apache.camel.handson.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Product implements Serializable {

    private static final long serialVersionUID = -7177449757160725980L;

    @Id
    private String productId;
    private String productName;
    private String productCategory;

}
