package com.example.demo.dao;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EmployeeDetails {
    private Integer id;
    private String firstName;
    private String lastName;
    private String jobTitle;
    private String departmentName;
    private Integer salary;
    private String location;
    private String managerName;
}
