package com.example.businessapp.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserDto implements Serializable {
    private Long id;
    private String username;
    private String email;
    private String role;
    private List<OrderDetailDto> orderDetails;

    @Data
    private static class OrderDetailDto implements Serializable {
        private Long id;
        private OrdersDto orders;
        private ProductDto product;
    }

    @Data
    private static class OrdersDto implements Serializable {
        private Long id;
        private String customerName;
    }

    @Data
    private static class ProductDto implements Serializable {
        private Long id;
        private String name;
    }
}
