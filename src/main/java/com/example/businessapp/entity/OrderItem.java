package com.example.businessapp.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Order_Details")
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

    private double amount; // tổng tiền = price * quantity

    private boolean check;

    private boolean active = true;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrderItem that = (OrderItem) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
