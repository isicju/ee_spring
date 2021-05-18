package com.example.demo;

import com.example.demo.controllers.EmployeeController;
import com.example.demo.dao.UserRepository;
import com.example.demo.services.MailService;
import com.example.demo.services.PdfGeneratorService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.sql.DataSource;
import java.io.File;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@ActiveProfiles("test")
//@SpringBootTest
//@AutoConfigureMockMvc
class DemoApplicationTests {

//	@Autowired
	private MockMvc mockMvc;

//	@Test
	void contextLoads() throws Exception{

		mockMvc.perform(get("http://localhost:8080/employees/")
				.contentType("application/json")
				.param("sendWelcomeMail", "true")
				.content("{}"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk());

//		String chromeDriverPath = (new File("src/test/resources").getAbsolutePath() + "\\chromedriver.exe");
//		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
//		WebDriver driver = new ChromeDriver();
//		driver.get("http://localhost:8080/");
//		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//		WebElement secondRecord = driver.findElement(By.xpath("//table/tbody/tr[2]"));
//		secondRecord.click();
//		WebElement commentsToReport = driver.findElement(By.id("messageId"));
//		commentsToReport.click();
//		commentsToReport.sendKeys("some details for report");
//
//		WebElement emailAddress = driver.findElement(By.id("exampleInputEmail"));
//		emailAddress.click();
//		emailAddress.sendKeys("isicju@gmail.com");
//
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("window.scrollBy(0,250)", "");
//
//		WebElement sendReportButton = driver.findElement(By.id("sendReport"));
//		Actions actions = new Actions(driver);
//		Thread.sleep(2000);
//		actions.moveToElement(sendReportButton).perform();
//
//		sendReportButton.click();
//
//		Thread.sleep(2000);
//		driver.quit();

	}





}
