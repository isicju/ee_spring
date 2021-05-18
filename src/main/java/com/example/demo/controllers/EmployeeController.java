package com.example.demo.controllers;

import com.example.demo.dao.Employee;
import com.example.demo.dao.EmployeeDetails;
import com.example.demo.dao.UserRepository;
import com.example.demo.model.MailDetails;
import com.example.demo.model.ReportRequest;
import com.example.demo.services.MailService;
import com.example.demo.services.PdfGeneratorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    final UserRepository userRepository;
    final PdfGeneratorService pdfService;
    final MailService mailService;

    @GetMapping(value = "/", produces = "application/json")
    public List<Employee> getEmployees() {
        return userRepository.getEmployees();
    }

    @GetMapping("/details/{id}")
    public EmployeeDetails getFullEmployeeDetails(@PathVariable("id") Integer id) {
        return userRepository.getEmployeeFullDetails(id);
    }

    @PostMapping(value = "/report", produces = "application/pdf")
    public @ResponseBody
    String getPdf(@Valid @RequestBody ReportRequest reportRequest) {
        EmployeeDetails employeeDetails =
                userRepository.getEmployeeFullDetails(reportRequest.getEmployeeId());
        return pdfService.generatePdf(employeeDetails, reportRequest.getReportMessage());
    }

    @PostMapping(value = "/mail")
    public @ResponseBody
    ResponseEntity sendPdfViaEmail(@RequestBody String mailDetailsJson) throws Exception {
        MailDetails mailDetails = MailDetails.buildAndValidate(mailDetailsJson);
        EmployeeDetails employeeDetails =
                userRepository.getEmployeeFullDetails(mailDetails.getEmployeeId());
        pdfService.generatePdf(employeeDetails, mailDetails.getReportMessage());
        mailService.sendEmail(mailDetails.getEmail());
        return ResponseEntity.status(200).build();
    }

}
