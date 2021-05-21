package com.example.demo.e2e;


import com.example.demo.DemoApplication;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UITests {

    @Test
    public void phantomjs() throws Exception {
        String[] args1 = {"--spring.profiles.active=test"};
        ConfigurableApplicationContext ctx =  SpringApplication.run(DemoApplication.class, args1);
        String chromeDriverPath = (new File("src/test/resources").getAbsolutePath() + "\\phantomjs.exe");
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        WebDriver driver = new PhantomJSDriver();
        driver.get("http://localhost:8080/");
        String secondTableRecord = "//table/tbody/tr[2]";
        WebElement secondRecord = driver.findElement(By.xpath(secondTableRecord));
        System.out.println(secondRecord.getText());
        secondRecord.click();
        Thread.sleep(1000);
        WebElement commentsToReport = driver.findElement(By.id("messageId"));
        commentsToReport.click();
        commentsToReport.sendKeys("some details for report");

        Thread.sleep(1000);

        WebElement emailAddress = driver.findElement(By.id("exampleInputEmail"));
        emailAddress.click();
        emailAddress.sendKeys("isicju@gmail.com");
        Thread.sleep(1000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");
        Thread.sleep(1000);
        WebElement sendReportButton = driver.findElement(By.id("sendReport"));
        Actions actions = new Actions(driver);
        actions.moveToElement(sendReportButton).perform();
        sendReportButton.click();
        Thread.sleep(10000);
        assertTrue(sendReportButton.getAttribute("class").contains("btn-success"));
        Thread.sleep(10000);
        ctx.close();
        driver.quit();

    }

    @Test
    public void uitest() throws Exception {
        String[] args1 = {"--spring.profiles.active=test"};
        ConfigurableApplicationContext ctx =  SpringApplication.run(DemoApplication.class, args1);

        String chromeDriverPath = (new File("src/test/resources").getAbsolutePath() + "\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebElement secondRecord = driver.findElement(By.xpath("//table/tbody/tr[2]"));
        secondRecord.click();
        WebElement commentsToReport = driver.findElement(By.id("messageId"));
        commentsToReport.click();
        commentsToReport.sendKeys("some details for report");
        WebElement emailAddress = driver.findElement(By.id("exampleInputEmail"));
        emailAddress.click();
        emailAddress.sendKeys("isicju@gmail.com");
        Thread.sleep(1000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");
        Thread.sleep(1000);
        WebElement sendReportButton = driver.findElement(By.id("sendReport"));
        Actions actions = new Actions(driver);
        actions.moveToElement(sendReportButton).perform();
        sendReportButton.click();
        Thread.sleep(10000);
        assertTrue(sendReportButton.getAttribute("class").contains("btn-success"));
        Thread.sleep(10000);
        ctx.close();
        driver.quit();
    }
}
