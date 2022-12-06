package com.example.businessapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "payment")
public class Payment implements Serializable {
    @Id
    private String id;

    private String productId;

    private String productName;

    private String paymentMethod;

    private String paymentGateway;

    private String orderId;

    private BigDecimal price;

    private String currency;

    private int quantity;

    private String unit;

    private BigDecimal amount;

    private String transaction;
}
