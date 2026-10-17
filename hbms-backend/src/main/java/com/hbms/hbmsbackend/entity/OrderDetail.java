package com.hbms.hbmsbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Order_Details")
@Data
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderDetail_ID")
    private Long orderDetailId;

    @Column(name = "Quantity", nullable = false)
    private Integer quantity;

    @Column(name = "Unit_Price", nullable = false)
    private Double unitPrice;

    @Column(name = "Customization")
    private String customization;

    // MANY OrderDetails belong to ONE Order
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Order_ID", nullable = false)
    private Order order;

    // MANY OrderDetails can reference ONE Product
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Product_ID", nullable = false)
    private Product product;
}