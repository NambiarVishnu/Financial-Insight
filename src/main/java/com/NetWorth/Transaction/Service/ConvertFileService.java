package com.NetWorth.Transaction.Service;

import com.aspose.pdf.ExcelSaveOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.aspose.pdf.ExcelSaveOptions;
import com.aspose.pdf.Document;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ConvertFileService {
    @Value("${file.upload}")
    private String uploadDir;

    private AtomicInteger fileCounter = new AtomicInteger(0);
    public  String pdfToExcel(MultipartFile file) throws IOException, IOException {

        Files.createDirectories(Paths.get(uploadDir));


        // Save the uploaded PDF file to a temporary file

        File tempPdfFile = File.createTempFile("uploaded-", ".pdf");
        file.transferTo(tempPdfFile);


        // Load the PDF using Aspose.PDF
        Document pdfDocument = new Document(tempPdfFile.getAbsolutePath());

        // Set Excel save options
        ExcelSaveOptions saveOptions = new ExcelSaveOptions();
        saveOptions.setMinimizeTheNumberOfWorksheets(true);

        // Specify the output Excel file path
        int fileId = fileCounter.incrementAndGet();
        String excelFileName = fileId + ".xlsx";
        String excelFilePath = uploadDir + File.separator + excelFileName;
        // Save the PDF as Excel
        pdfDocument.save(excelFilePath, saveOptions);

        // Clean up temporary PDF file
        tempPdfFile.delete();

        return excelFilePath; // Return the path to the Excel file
    }
}