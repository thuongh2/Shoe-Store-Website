package com.example.businessapp.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "product")
public class Product implements Serializable {
    // id and auto increment
    @Id
    private String id;

    private String name;

    private String description;

    private BigDecimal price;

    private List<String> image;

    private List<String> sizes;

    private String categoryId;

    private int discount;

    private boolean active;

    private BigDecimal promotionalPrice;

    private int sold;

    private boolean isSelling;

    private int rating;
}
