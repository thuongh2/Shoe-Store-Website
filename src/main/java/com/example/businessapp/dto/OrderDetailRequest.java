package com.example.businessapp.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OrderDetailRequest {
    @NotNull
    private Long id;
    @NotNull
    private Long orderId;
    @NotNull
    private Long productId;
    @NotNull
    private int size;
    @NotNull
    private int quantity;
}
