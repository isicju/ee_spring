package com.example.demo.model;

import com.example.demo.validation.IllegalRequestInputException;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import lombok.Data;

import static org.apache.commons.validator.routines.EmailValidator.getInstance;

@Data
public class MailDetails {
    private Integer employeeId;
    private String email;
    private String reportMessage;
    public static MailDetails buildAndValidate(String json) {
        try {
            Gson gson = new Gson();
            MailDetails mailDetails = gson.fromJson(json, MailDetails.class);

            if(mailDetails.getReportMessage() == null){
                throw new IllegalRequestInputException("{\"error\":\"provide valid reportMessage!\"}");
            }

            if(mailDetails.getEmployeeId() == null || mailDetails.getEmployeeId() < 0){
                throw new IllegalRequestInputException("{\"error\":\"provide valid employee id!\"}");
            }

            if (!getInstance().isValid(mailDetails.getEmail())) {
                throw new IllegalRequestInputException("{\"error\":\"provide valid email!\"}");
            }

            return mailDetails;
        } catch (JsonSyntaxException e) {
            throw new IllegalRequestInputException("{\"error\": \"body is not valid json\"");
        }
    }

}

