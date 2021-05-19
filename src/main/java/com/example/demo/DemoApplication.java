package com.example.demo;

import com.example.demo.services.MailService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class DemoApplication {


    public static void main(String[] args) throws Exception {

        ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
    }

}
