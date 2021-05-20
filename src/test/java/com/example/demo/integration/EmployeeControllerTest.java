package com.example.demo.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import javax.sql.DataSource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	DataSource dataSource;

	@Test
	public void getAllEmployees() throws Exception{
		mockMvc.perform(get("http://localhost:8080/employees/")
				.contentType("application/json")
				.content("{}"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk());
	}

	@Test
	public void contextLoads() throws Exception{
		mockMvc.perform(get("http://localhost:8080/employees/")
				.contentType("application/json")
				.content("{}"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk());
	}




}
