package com.hbms.hbmsbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Recipe")
@Data
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Recipe_ID")
    private Long recipeId;

    @Column(name = "Quantity_Required", nullable = false)
    private Double quantityRequired;

    // MANY Recipes belong to ONE Product
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Product_ID", nullable = false)
    private Product product;

    // MANY Recipes use ONE Ingredient
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Ingredient_ID", nullable = false)
    private Inventory ingredient;
}