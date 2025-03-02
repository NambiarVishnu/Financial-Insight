package com.NetWorth.Transaction.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Bank-data-2")
public class BankData2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="transactiondetails")
    private String txn;


    @Column(name="balance")
    private Double amount;

    @Column(name="withdrawals")
    private Double withdrawals;

    @Column(name="deposits")
    private Double deposits;


    @Column(name="date")
    private Date date;


}