package com.example.businessapp.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class ProductRequest {
    private Long id;
    @NotNull(message = "name not null")
    private String name;
    @NotBlank(message = "description not blank")
    private String description;
    @Min(value = 0, message = "price is positive")
    private double price;
    @Min(value = 0, message = "categoryId is positive")
    private Long categoryId;
}
