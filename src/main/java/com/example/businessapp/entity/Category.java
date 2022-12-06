package com.example.businessapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "category")
public class Category {
    @Id
    private String id;

    private String name;

    private String slug;

    private String image;

    private boolean isDelete;
}
