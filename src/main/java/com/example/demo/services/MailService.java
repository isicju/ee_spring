package com.example.demo.services;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.StringWriter;
import java.util.Properties;

@Log4j2
@AllArgsConstructor
@Service
public class MailService {

    private final Properties emailProperties;

    public String sendEmail(String toEmail) {

        return "Email sent!";
    }

    public void send(Message message) throws Exception{
    }

}
