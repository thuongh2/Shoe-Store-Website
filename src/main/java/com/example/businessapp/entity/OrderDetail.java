package com.example.businessapp.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Order_Details")
public class OrderDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID", nullable = false, //
            foreignKey = @ForeignKey(name = "ORDER_DETAIL_ORD_FK"))
    @ToString.Exclude
    private Orders orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", nullable = false, //
            foreignKey = @ForeignKey(name = "ORDER_DETAIL_PROD_FK"))
    @ToString.Exclude
    private Product product;

    @Column(name = "size", nullable = false)
    private int size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false, //
            foreignKey = @ForeignKey(name = "ORDER_DETAIL_USER_FK"))
    @ToString.Exclude
    private User user;

    @Column(name = "QUANTITY", nullable = false)
    private int quantity;

    @Column(name = "Price", nullable = false)
    private double price; // giá của sản phẩm

    @Column(name = "Amount", nullable = false)
    private double amount; // tổng tiền = price * quantity

    @Column(name= "is_check", columnDefinition = "boolean default false")
    private boolean check;

    @Column(columnDefinition = "boolean default true")
    private boolean active = true;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrderDetail that = (OrderDetail) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
