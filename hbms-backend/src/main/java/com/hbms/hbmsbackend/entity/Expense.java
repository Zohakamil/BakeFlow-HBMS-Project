package com.hbms.hbmsbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "Expense")
@Data
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Expense_ID")
    private Long expenseId;

    @Column(name = "Category", nullable = false)
    private String category;

    @Column(name = "Amount", nullable = false)
    private Double amount;

    @Column(name = "Expense_Date", nullable = false)
    private LocalDate expenseDate;

    @Column(name = "Description")
    private String description;
}