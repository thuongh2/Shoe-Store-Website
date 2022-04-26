package com.example.businessapp.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class SizeDto implements Serializable {
    private Long id;
    @Min(value = 0, message = "size not 0")
    private int size;
    @Min(value = 0, message = "quantity not 0")
    private int quantity;
    private ProductDto productDto;

    @Data
    public static class ProductDto implements Serializable {
        private Long id;
        private String name;
    }
}
