package com.hbms.hbmsbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Vendor")
@Data
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Vendor_ID")
    private Long vendorId;

    @Column(name = "Vendor_Name", nullable = false)
    private String vendorName;

    @Column(name = "Contact")
    private String contact;
}