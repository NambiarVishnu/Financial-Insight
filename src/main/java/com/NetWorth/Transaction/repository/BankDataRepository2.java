package com.NetWorth.Transaction.repository;

import com.NetWorth.Transaction.model.BankData2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankDataRepository2 extends JpaRepository<BankData2,Integer> {
}