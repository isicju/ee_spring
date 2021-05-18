package com.example.demo.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Data
public class ReportRequest {
    private String reportMessage;
    private Integer employeeId;
}
