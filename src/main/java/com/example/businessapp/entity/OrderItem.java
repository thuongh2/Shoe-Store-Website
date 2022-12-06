package com.example.businessapp.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "order_item")
public class OrderItem implements Serializable {
    @Id
    private String id;

    private String orderId;

    private String productId;

    private String paymentId;

    private int size;

    private String userId;

    private int quantity;

    private BigDecimal price; // giá của sản phẩm

    private BigDecimal amount; // tổng tiền = price * quantity

    private boolean check;

    private boolean active;

}
