package com.hbms.hbmsbackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

@Entity
@Table(name = "Customers") // Matches your SQL table name
@Data // Lombok annotation: generates Getters, Setters, toString, etc.
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Customer_ID")
    private Long customerId;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Address")
    private String address;

    @Column(name = "Email", unique = true)
    private String email;

    // One Customer can place Many Orders
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders;
}