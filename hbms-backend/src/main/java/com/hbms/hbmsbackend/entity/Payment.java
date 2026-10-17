package com.hbms.hbmsbackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "Payment")
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Payment_ID")
    private Long paymentId;

    @Column(name = "Payment_Method", nullable = false)
    private String paymentMethod;

    @Column(name = "Amount_Paid", nullable = false)
    private Double amountPaid;

    @Column(name = "Payment_Date")
    private LocalDate paymentDate;

    @Column(name = "Payment_Status")
    private String paymentStatus;

    // ONE Payment belongs to ONE Order (One-to-One)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Order_ID", nullable = false, unique = true)
    private Order order;
}