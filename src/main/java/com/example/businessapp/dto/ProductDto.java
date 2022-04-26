package com.example.businessapp.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
public class ProductDto implements Serializable {

    private Long id;
    @NotNull(message = "name not null")
    private String name;
    @NotBlank(message = "description not blank")
    private String description;
    @Min(value = 0, message = "price is positive")
    private double price;
    private List<ImageDto> image;
    private List<SizeDto> sizes;
    private CategoryDto category;


    @Data
    public static class ImageDto implements Serializable {
        private Long id;
        private String thumbnail;
    }

    @Data
    public static class SizeDto implements Serializable {
        private Long id;
        private String size;
        private int quantity;
    }

}
