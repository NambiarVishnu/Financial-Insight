package com.NetWorth.Transaction.Controller;

import com.NetWorth.Transaction.Service.ConvertFileService;
import com.NetWorth.Transaction.Service.TransactionExtractor;
import com.NetWorth.Transaction.Service.TransactionSave;
import com.NetWorth.Transaction.Service.TransactionSave2;
import com.NetWorth.Transaction.repository.BankDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class TransactionExtractorController {

        @Autowired
        private TransactionExtractor transactionExtractor;
        @Autowired
        private ConvertFileService convertFileService;
        @Autowired
        private BankDataRepository bankDataRepository;
        @Autowired
        private TransactionSave transactionSave;

        @Autowired
        private TransactionSave2 transactionSave2;

//        private ExecutorService service= Executors.newFixedThreadPool(2);

        @PostMapping("/upload-statement")
        public ResponseEntity<?> handleFile(@RequestParam("file") List<MultipartFile> files,
                                            @RequestParam(value = "h1",  defaultValue = "Particulars") String header1,
                                            @RequestParam(value = "h2",  defaultValue = "Balance") String header2,
                                            @RequestParam(value = "h3",  defaultValue = "Withdrawals") String header3,
                                            @RequestParam(value = "h4",  defaultValue = "Deposits") String header4,
                                            @RequestParam(value = "h5",  defaultValue = "Date") String header5)
        {
            Instant startTime = Instant.now();  // Capture start time

            MultipartFile file1 = files.get(0);
            MultipartFile file2 = files.get(1);



            if (file1.isEmpty()||file2.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("INVALID FILE");

            }
            try {
                // Convert PDF to Excel

                String excelFile1 = convertFileService.pdfToExcel(file1);
                if(excelFile1==null){
                    System.out.println("Failed to Conert excel to pdf");
                }
                List<Map<String, Object>> transactionDetails = transactionExtractor.extractDetails(excelFile1, header1, header2,header3,header4,header5,1,100);

                transactionSave.saveExtractedData(transactionDetails);
                System.out.println("done1");

                String excelFile2 = convertFileService.pdfToExcel(file2);
                if(excelFile2==null){
                    System.out.println("Failed to Conert excel to pdf");
                }
                List<Map<String,Object>> transactionDetails2 = transactionExtractor.extractDetails(excelFile2,header1,header2,header3,header4,header5,1,150);
                transactionSave2.saveExtractedData(transactionDetails2);
                System.out.println("done 2");
//            return ResponseEntity.ok(transactionDetails);
                Map<String, Object> response = new HashMap<>();
                response.put("transactionDetails", transactionDetails);
                response.put("transactionDetails2", transactionDetails2);
                Instant endSavingTime = Instant.now();
                Duration totalDuration = Duration.between(startTime, endSavingTime);
                System.out.println(totalDuration);

//            Pair<List<Map<String, Object>>,List<Map<String, Object>>> response = Pair.of(transactionDetails, transactionDetails2);
                return ResponseEntity.ok(response);

//            return ResponseEntity.ok(response);

            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(500).body("Failed to upload or convert file: " + e.getMessage());
            }
        }
    }

