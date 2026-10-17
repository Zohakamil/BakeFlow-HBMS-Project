package com.hbms.hbmsbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Delivery")
@Data
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Delivery_ID")
    private Long deliveryId;

    @Column(name = "Rider_Name")
    private String riderName;

    @Column(name = "Delivery_Status")
    private String deliveryStatus;

    // ONE Delivery belongs to ONE Order (One-to-One)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Order_ID", nullable = false, unique = true)
    private Order order;
}