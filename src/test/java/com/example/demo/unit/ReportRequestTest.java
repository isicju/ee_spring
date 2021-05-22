package com.example.demo.unit;

import com.example.demo.model.ReportRequest;
import com.example.demo.validation.IllegalRequestInputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReportRequestTest {

    @Test
    public void validParsingReportRequest(){
        String validRequest = "{\"reportMessage\":\"blablabla\",\"employeeId\":\"123\"}";
        ReportRequest reportRequest = ReportRequest.buildAndValidate(validRequest);
        assertEquals("blablabla", reportRequest.getReportMessage());
        assertNotNull(reportRequest.getEmployeeId());
    }

    @Test
    public void invalidParsingReportRequest(){
        assertThrows(IllegalRequestInputException.class,()->{
            String validRequest = "{\"reportMessage\":\"blaloyeeId\":\"123\"}";
            ReportRequest reportRequest = ReportRequest.buildAndValidate(validRequest);
        });
    }

}
