package com.example.demo.integration.services;

import com.example.demo.dao.EmployeeDetails;
import com.example.demo.services.PdfGeneratorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class PdfGeneratorServiceTest {


    @Autowired
    PdfGeneratorService pdfGeneratorService;

    @Test
    public void checkOutgoingEmailAddress() throws Exception {
        EmployeeDetails employeeDetails = EmployeeDetails
                .builder()
                .id(12)
                .firstName("john")
                .lastName("smith")
                .build();
        String generatedData = pdfGeneratorService.generatePdf(employeeDetails, "messsage");
        Assertions.assertFalse(generatedData.isEmpty());
    }

}
