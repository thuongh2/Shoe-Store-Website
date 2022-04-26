package com.example.businessapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "category")
public class Category extends Auditable<String> implements Serializable {
    @Id
    @Column(name = "id", length = 50, nullable = false)
    private Long id;

    @Column(name = "Name", length = 255, nullable = false)
    private String name;


    @Column(columnDefinition = "boolean default true")
    private boolean active;

}
