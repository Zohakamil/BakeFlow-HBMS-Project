package com.hbms.hbmsbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Product_ID")
    private Long productId;

    @Column(name = "Product_Name", nullable = false)
    private String productName;

    @Column(name = "Base_Price", nullable = false)
    private Double basePrice;

    // Optional: Add SRS fields for Size and Description later if needed
}