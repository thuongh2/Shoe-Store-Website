package com.example.businessapp.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ImageDto implements Serializable {
    private Long id;
    private String thumbnail;
    private ProductDto product;

    @Data
    public static class ProductDto implements Serializable {
        private Long id;
        private String name;
    }
}
