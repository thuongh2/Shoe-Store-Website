package com.example.businessapp.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Image implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "thumbnail", nullable = false)
    private String thumbnail;

    @Column(columnDefinition = "boolean default true")
    private boolean active = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="product_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Product product;

}
