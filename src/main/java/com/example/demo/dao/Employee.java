package com.example.demo.dao;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Builder
@Data
public class Employee {
    private Integer id;
    private String firstName;
    private String lastName;
    private String hireDate;
    private String email;
    private Integer salary;
}
