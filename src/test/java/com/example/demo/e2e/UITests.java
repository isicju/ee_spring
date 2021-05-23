package com.example.demo.e2e;


import com.example.demo.DemoApplication;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.io.File;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UITests {

    private String host;
    private String recipientMail;

    @BeforeEach
    public void init() {
        this.host = Optional.ofNullable(System.getProperty("uitest.host"))
                .orElseThrow(() -> new RuntimeException("please pass argument -Duitest.host"));
        this.recipientMail = Optional.ofNullable(System.getProperty("uitest.mail"))
                .orElseThrow(() -> new RuntimeException("please pass argument -Duitest.mail"));
    }

    @Test
    public void phantomDriver() throws Exception {
        uiTest(new PhantomJSDriver());
    }

    @Test
    public void chromeDriver() throws Exception {
        String chromeDriverPath = (new File("src/test/resources").getAbsolutePath() + "\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        uiTest(new ChromeDriver());
    }

    private void uiTest(WebDriver driver) throws Exception {
        String[] args1 = {"--spring.profiles.active=test"};
        ConfigurableApplicationContext ctx = SpringApplication.run(DemoApplication.class, args1);
        Thread.sleep(15000);
        driver.get("http://" + this.host);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebElement secondRecord = driver.findElement(By.xpath("//table/tbody/tr[2]"));
        secondRecord.click();
        WebElement commentsToReport = driver.findElement(By.id("messageId"));
        commentsToReport.click();
        commentsToReport.sendKeys("some details for report");
        WebElement emailAddress = driver.findElement(By.id("exampleInputEmail"));
        emailAddress.click();
        emailAddress.sendKeys(this.recipientMail);
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
