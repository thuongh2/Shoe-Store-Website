package com.example.businessapp.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OrdersDto implements Serializable {
    private Long id;
    private double amount;
    private String customerName;
    private String customerAddress;
    private String customerEmail;
    private String customerPhone;
    private Date createDate;
}
