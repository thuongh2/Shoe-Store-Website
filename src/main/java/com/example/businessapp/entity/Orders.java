package com.example.businessapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Orders")
public class Orders extends Auditable<String> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Tong so tien da dat
    @Column(name = "Amount", nullable = false)
    private double amount = 0;

    @Column(name = "Customer_Name", length = 255, nullable = false)
    @NotNull(message = "customer name is not null")
    private String customerName;

    @Column(name = "Customer_Address", length = 255, nullable = false)
    @NotNull(message = "customer address is not null")
    private String customerAddress;

    @Column(name = "Customer_Email", length = 128, nullable = false)
    @Email(message = "customer email is not null")
    private String customerEmail;

    @Column(name = "Customer_Phone", length = 128, nullable = false)
    @NotNull(message = "customer name is not null")
    private String customerPhone;


    @Column(columnDefinition = "boolean default true")
    private boolean active = true;

}
