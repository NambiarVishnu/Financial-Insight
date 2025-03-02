package com.NetWorth.Transaction.Service;


import com.NetWorth.Transaction.model.BankData;
import com.NetWorth.Transaction.model.BankData2;
import com.NetWorth.Transaction.repository.BankDataRepository;
import com.NetWorth.Transaction.repository.BankDataRepository2;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class TransactionSave2 {

    @Autowired
    private BankDataRepository2 bankDataRepository2;

    @Transactional
    public void saveExtractedData(List<Map<String, Object>> transactionDetails) throws IllegalAccessException {
        if(transactionDetails==null||transactionDetails.isEmpty()){
            throw new IllegalAccessException("No transaction details provided");
        }

        List<BankData2> transactions =new ArrayList<>();
        for (Map<String, Object> data : transactionDetails) {
            System.out.println("Transaction Map: " + data); // Print the entire map

            try{
                // Extract transaction fields from the map
                String transactionId = (String) data.get("Particulars");
                Double amount = Double.parseDouble((String) data.get("Balance")) ;
                Double withdrawals=Double.parseDouble((String) data.get("Withdrawals"));
                Double deposits=Double.parseDouble((String) data.get("Deposits"));
                String dateString = (String) data.get("Date"); // Assuming the value is a String
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = dateFormat.parse(dateString);
                System.out.println(transactionId + "-----"+amount);

                // Create a new BankData object
                BankData2 transaction = new BankData2();
                transaction.setTxn(transactionId);
                transaction.setAmount(amount);
                transaction.setWithdrawals(withdrawals);
                transaction.setDeposits(deposits);
                transaction.setDate(date);


                // Save the transaction data to the database
                transactions.add(transaction);
            }catch(Exception e){
                throw new RuntimeException("Failed to process transaction: "+e.getMessage());
            }
            bankDataRepository2.saveAll(transactions);

        }

    }
}