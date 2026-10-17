package com.hbms.hbmsbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Inventory")
@Data
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Ingredient_ID")
    private Long ingredientId;

    @Column(name = "Ingredient_Name", nullable = false)
    private String ingredientName;

    @Column(name = "Quantity_Stock", nullable = false)
    private Double quantityStock;

    @Column(name = "Unit", nullable = false)
    private String unit; // kg, pieces, liters

    // MANY Ingredients are supplied by ONE Vendor
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Vendor_ID")
    private Vendor vendor;
}