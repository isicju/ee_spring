package com.example.demo.integration.controllers;

import com.example.demo.dao.Employee;
import com.example.demo.dao.UserRepository;
import com.example.demo.services.MailService;
import com.example.demo.services.PdfGeneratorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerWithMocks {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserRepository userRepository;

    @MockBean
    PdfGeneratorService pdfService;

    @MockBean
    MailService mailService;

    @Test
    public void getAllEmployees() throws Exception {
        List<Employee> mockedEmployees = new ArrayList<>();
        mockedEmployees.add(Employee.builder()
                .firstName("John")
                .lastName("Smith")
                .build());

        Mockito.when(userRepository.getEmployees()).thenReturn(mockedEmployees);

        mockMvc.perform(get("http://localhost:8080/employees/")
                .contentType("application/json")
                .content("{}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(jsonPath("$[0].firstName",is("John")) )
                .andExpect(jsonPath("$[0].lastName",is("Smith")) );

    }

    @Test
    public void getAllEmployeesNone() throws Exception{
        when(userRepository.getEmployees()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("http://localhost:8080/employees/")
                .contentType("application/json")
                .content("{}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().json("[]"));
    }

    @Test
    public void getAllEmployeesError() throws Exception{
        when(userRepository.getEmployees()).thenThrow(new RuntimeException());
        mockMvc.perform(get("http://localhost:8080/employees/")
                .contentType("application/json")
                .content("{}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is5xxServerError())
                .andExpect(content().string(containsString("Please contact administrator")));
    }

    @Test
    public void getReport() throws Exception{
        when(userRepository.getEmployeeFullDetails(any())).thenThrow(new RuntimeException(""));

        mockMvc.perform(post("http://localhost:8080/employees/report/")
                .contentType("application/json")
                .content("{}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is5xxServerError())
                .andExpect(content().string(containsString("Please contact administrator")));
    }

    @Test
    void getAllEmployeesOneEmployee() throws Exception{
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(Employee.builder()
                .firstName("John")
                .lastName("Smith")
                .build());
        when(userRepository.getEmployees()).thenReturn(employeeList);
        mockMvc.perform(get("http://localhost:8080/employees/")
                .contentType("application/json")
                .content("{}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(jsonPath("$[0].firstName",is("John")) )
                .andExpect(jsonPath("$[0].lastName",is("Smith")) );
    }

    @Test
    public void spyExample() throws Exception{

        mockMvc.perform(get("http://localhost:8080/employees/")
                .contentType("application/json")
                .content("{}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().json("[]"));


    }




}
