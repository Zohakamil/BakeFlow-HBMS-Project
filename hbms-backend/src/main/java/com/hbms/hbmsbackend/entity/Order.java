package com.hbms.hbmsbackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Orders") // 'Order' is reserved in SQL, so we map to 'Orders'
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Order_ID")
    private Long orderId;

    @Column(name = "Order_Date", nullable = false)
    private LocalDate orderDate;

    @Column(name = "Delivery_Date")
    private LocalDate deliveryDate;

    @Column(name = "Order_Status", nullable = false)
    private String orderStatus; // Placed, Baking, Completed, Out for Delivery, Delivered

    @Column(name = "Total_Price", nullable = false)
    private Double totalPrice;

    // MANY Orders belong to ONE Customer
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Customer_ID", nullable = false)
    private Customer customer;

    // ONE Order can have MANY OrderDetails
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;
}