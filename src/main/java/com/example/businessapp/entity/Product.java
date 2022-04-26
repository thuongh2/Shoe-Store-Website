package com.example.businessapp.entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

//@JsonIgnoreProperties({"hibernateLazyInitializer"})
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "Products")
public class Product extends Auditable<String> implements Serializable {

    // id and auto increment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //name of product
    @Column(name = "name", length = 255, nullable = false)
    @NonNull
    private String name;
    // description and notblank
    @Column(name = "Description", nullable = true, columnDefinition = "TEXT")
    @NotBlank(message = "description not blank")
    private String description;

    @Column(name = "Price", nullable = false)
    @Min(value = 0, message = "price is positive")
    private double price;

    //List image of product
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Image> image;

    //size image of product
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Size> sizes;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    private boolean active;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Product product = (Product) o;
        return id != null && Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
