package com.example.businessapp.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderDetailDto implements Serializable {
    private Long id;
    private OrdersDto orders;
    private ProductDto product;
    private int quantity;
    private double price;
    private double amount;

    @Data
    public static class OrdersDto implements Serializable {
        private Long id;
        private String customerName;
    }

    @Data
    public static class ProductDto implements Serializable {
        private Long id;
        private String name;
    }


}
