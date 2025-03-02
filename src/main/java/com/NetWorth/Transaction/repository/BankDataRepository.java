package com.NetWorth.Transaction.repository;

import com.NetWorth.Transaction.model.BankData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankDataRepository extends JpaRepository<BankData,Long> {
}