package com.example.demo.model;

import com.example.demo.validation.IllegalRequestInputException;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import lombok.Data;

@Data
public class MailDetails {
    private Integer employeeId;
    private String email;
    private String reportMessage;
    public static MailDetails buildAndValidate(String json) {
        try {
            Gson gson = new Gson();

            return gson.fromJson(json, MailDetails.class);
        } catch (JsonSyntaxException e) {
            throw new IllegalRequestInputException("{\"error\": \"body is not valid json\"");
        }
    }

}

