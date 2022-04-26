package com.example.businessapp.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Size implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "size", nullable = false, unique = true, columnDefinition = "integer default 0")
    private int size;

    @Column(name = "quantity", nullable = false, columnDefinition = "integer default 0")
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(columnDefinition = "boolean default true")
    private boolean active = true;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Size size = (Size) o;
        return id != null && Objects.equals(id, size.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
