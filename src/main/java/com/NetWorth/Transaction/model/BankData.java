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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Bank-data-1")
public class BankData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="transactiondetails")
    private String txndetails;

    @Column(name="balance")
    private Double amount;

    @Column(name="withdrawals")
    private Double withdrawals;

    @Column(name="deposits")
    private Double deposits;


    @Column(name="date")
    private Date date;



}