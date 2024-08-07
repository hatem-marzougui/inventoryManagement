package com.example.ims.model;

import com.example.ims.constants.TransactionType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer productId;

    private Integer employeeId;

    private Integer quantity;

    private LocalDateTime date;

    private TransactionType transactionType;

    @ManyToOne
    @JoinColumn(name = "employeeId", referencedColumnName = "id", insertable = false, updatable = false)
    private Employee employee;


}
