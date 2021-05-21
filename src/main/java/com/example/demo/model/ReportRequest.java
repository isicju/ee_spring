package com.example.demo.model;

import com.example.demo.validation.IllegalRequestInputException;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import lombok.Data;

@Data
public class ReportRequest {
    private String reportMessage;
    private Integer employeeId;

    public static ReportRequest buildAndValidate(String json) {
        try {
            Gson gson = new Gson();
            ReportRequest reportRequest = gson.fromJson(json, ReportRequest.class);

            if(reportRequest.getReportMessage() == null){
                throw new IllegalRequestInputException("{\"error\":\"provide valid reportMessage!\"}");
            }

            if(reportRequest.getEmployeeId() == null || reportRequest.getEmployeeId() < 0){
                throw new IllegalRequestInputException("{\"error\":\"provide valid employee id!\"}");
            }

            return reportRequest;
        } catch (JsonSyntaxException e) {
            throw new IllegalRequestInputException("{\"error\": \"body is not valid json\"");
        }
    }

}
