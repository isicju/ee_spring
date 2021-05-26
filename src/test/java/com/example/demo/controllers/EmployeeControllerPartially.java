package com.example.demo.controllers;

import com.example.demo.dao.EmployeeDetails;
import com.example.demo.dao.UserRepository;
import com.example.demo.services.PdfGeneratorService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import javax.sql.DataSource;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("prod")
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class EmployeeControllerPartially {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    DataSource dataSource;

    @MockBean
    UserRepository userRepository;

    @SpyBean
    PdfGeneratorService pdfService;



    @Test
    public void getAllEmployeesWithStubbedId() throws Exception {

        when(userRepository.getEmployeeFullDetails(any())).thenReturn(
                EmployeeDetails.builder().id(104).firstName("John").lastName("Smith").jobTitle("AVP")
                        .build()
        );

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

        assertEquals("John",employeeCapture.getValue().getFirstName());
        assertEquals("Smith",employeeCapture.getValue().getLastName());
        assertEquals("AVP",employeeCapture.getValue().getJobTitle());
        assertEquals(104,employeeCapture.getValue().getId().longValue());
    }

}
