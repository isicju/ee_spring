package com.example.demo.integration.controllers;

import com.example.demo.controllers.EmployeeController;
import com.example.demo.dao.Employee;
import com.example.demo.dao.EmployeeDetails;
import com.example.demo.dao.UserRepository;
import com.example.demo.services.MailService;
import com.example.demo.services.PdfGeneratorService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(EmployeeController.class)
class EmployeeControllerWithMocks {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserRepository userRepository;

    @MockBean
    PdfGeneratorService pdfGeneratorService;

    @MockBean
    MailService mailService;

    @MockBean
    PdfGeneratorService pdfService;

    @Test
    public void getAllEmployees() throws Exception {
        mockMvc.perform(post("http://localhost:8080/employees/report")
                .contentType("application/json")
                .content("{\"reportMessage\": \"blabla\", \"employeeId\":\"103\"}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

        ArgumentCaptor<Integer> employeeCaptureId = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<EmployeeDetails> employeeCapture = ArgumentCaptor.forClass(EmployeeDetails.class);

        Mockito.verify(userRepository).getEmployeeFullDetails(employeeCaptureId.capture());
        Mockito.verify(pdfService).generatePdf(employeeCapture.capture(), any());

        assertEquals(103,employeeCaptureId.getValue().intValue());

        assertEquals("Alexander",employeeCapture.getValue().getFirstName());
        assertEquals("Hunold",employeeCapture.getValue().getLastName());
        assertEquals("Programmer",employeeCapture.getValue().getJobTitle());
        assertEquals(103,employeeCapture.getValue().getId().longValue());
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
        when(userRepository.getEmployeeFullDetails(anyInt())).thenThrow(new RuntimeException());
        mockMvc.perform(get("http://localhost:8080/report/")
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
                .andExpect(jsonPath("$[0].firstName",is("lalala")) )
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
