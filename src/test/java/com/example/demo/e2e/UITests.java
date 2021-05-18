package com.example.demo.e2e;

//
//import com.example.demo.DemoApplication;
//import org.junit.jupiter.api.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.phantomjs.PhantomJSDriver;
//import org.springframework.context.ConfigurableApplicationContext;
//
//import java.io.File;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.atomic.AtomicBoolean;

public class UITests {

//    @Test
//    public void uitest() throws Exception {
////        String[] args1 = {"--spring.profiles.active=test"};
//
//        try {
//            System.out.println("waiting while spring is starting...");
//            Thread.sleep(8000);
//            System.out.println("initializing phantom js");
//            String chromeDriverPath = (new File("src/test/resources").getAbsolutePath() + "\\phantomjs");
//            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
//            WebDriver driver = new PhantomJSDriver();
//            System.out.println("opening url");
//            driver.get("http://185.204.3.211:8080/");
//            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//            System.out.println("searching for 2nd record in url");
//
//            WebElement secondRecord = driver.findElement(By.xpath("//table/tbody/tr[2]"));
//            secondRecord.click();
//            WebElement commentsToReport = driver.findElement(By.id("messageId"));
//            commentsToReport.click();
//            commentsToReport.sendKeys("some details for report from ui test");
//
//            WebElement emailAddress = driver.findElement(By.id("exampleInputEmail"));
//            emailAddress.click();
//            emailAddress.sendKeys("isicju@gmail.com");
//
//            JavascriptExecutor js = (JavascriptExecutor) driver;
//            js.executeScript("window.scrollBy(0,250)", "");
//
//            WebElement sendReportButton = driver.findElement(By.id("sendReport"));
//            Actions actions = new Actions(driver);
//            Thread.sleep(2000);
//            actions.moveToElement(sendReportButton).perform();
//
//            sendReportButton.click();
//
//            driver.quit();
//            System.out.println("Successfully run UI TEST!");
//            Thread.sleep(2000);
//
//        } catch (Exception e) {
//            System.out.println("Error running test: " + e.getMessage());
//        }
//
//    }
//
//    public static void main2(String[] args) throws Exception {
//        String chromeDriverPath = (new File("src/test/resources").getAbsolutePath() + "\\phantomjs.exe");
//        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
//        WebDriver driver = new PhantomJSDriver();
//        driver.get("http://localhost:8080/");
//        String secondTableRecord = "//table/tbody/tr[2]";
//        WebElement secondRecord = driver.findElement(By.xpath(secondTableRecord));
//        System.out.println(secondRecord.getText());
//        driver.quit();
//    }

//    public static void main(String[] args) throws Exception {
//        DemoApplication demoApplication = new DemoApplication();
//        demoApplication.main(new String[]{});
//
//
//        String chromeDriverPath = (new File("src/test/resources").getAbsolutePath() + "\\chromedriver.exe");
//        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
//        WebDriver driver = new ChromeDriver();
//        driver.get("http://localhost:8080/");
//        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//        WebElement secondRecord = driver.findElement(By.xpath("//table/tbody/tr[2]"));
//        secondRecord.click();
//        WebElement commentsToReport = driver.findElement(By.id("messageId"));
//        commentsToReport.click();
//        commentsToReport.sendKeys("some details for report");
//
//        WebElement emailAddress = driver.findElement(By.id("exampleInputEmail"));
//        emailAddress.click();
//        emailAddress.sendKeys("isicju@gmail.com");
//
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.scrollBy(0,250)", "");
//
//        WebElement sendReportButton = driver.findElement(By.id("sendReport"));
//        Actions actions = new Actions(driver);
//        Thread.sleep(2000);
//        actions.moveToElement(sendReportButton).perform();
//
//        sendReportButton.click();
//
//        Thread.sleep(2000);
//        driver.quit();
//    }
}
