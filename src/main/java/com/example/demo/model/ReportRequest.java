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
            return gson.fromJson(json, ReportRequest.class);
        } catch (JsonSyntaxException e) {
            throw new IllegalRequestInputException("{\"error\": \"body is not valid json\"");
        }
    }

}
