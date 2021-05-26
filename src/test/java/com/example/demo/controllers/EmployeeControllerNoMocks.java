package com.example.demo.controllers;

import com.example.demo.dao.Employee;
import com.example.demo.services.MailService;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("prod")
@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerNoMocks {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    DataSource dataSource;

    @Autowired
    MailService mailService;

    @Test
    public void getAllEmployees() throws Exception {
        mockMvc.perform(get("http://localhost:8080/employees/")
                .contentType("application/json")
                .content("{}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    public void getEmployeeDetails() throws Exception {
        MvcResult employees = mockMvc.perform(get("http://localhost:8080/employees/")
                .contentType("application/json")
                .content("{}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();
        List<Employee> list = Arrays.asList(new GsonBuilder().create()
                .fromJson(employees.getResponse().getContentAsString(), Employee[].class));

        mockMvc.perform(get("http://localhost:8080/employees/details/" + list.get(0).getId())
                .contentType("application/json")
                .content("{}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName",notNullValue()))
                .andExpect(jsonPath("$.lastName",notNullValue()))
                .andExpect(jsonPath("$.departmentName",notNullValue()))
                .andExpect(jsonPath("$.location",notNullValue()));
    }


    @Test
    public void getEmployeeDetailsNotExist() throws Exception {
        mockMvc.perform(get("http://localhost:8080/employees/details/")
                .contentType("application/json")
                .content("{}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void getEmployeeDetailsWrongId() throws Exception {
        mockMvc.perform(get("http://localhost:8080/employees/details/888")
                .contentType("application/json")
                .content("{}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is5xxServerError());
    }
}
