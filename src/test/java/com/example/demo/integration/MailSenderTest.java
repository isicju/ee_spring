package com.example.demo.integration;

import com.example.demo.services.MailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.powermock.api.mockito.PowerMockito.spy;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class MailSenderTest {

    @Qualifier("emailProperties")
    @Autowired
    Properties properties;

    @Autowired
    MailService mailService;

    @Test
    void checkOutgoingEmailAddress() throws Exception {
        final String[] fromResult = new String[1];
        final AtomicBoolean wasExecuted  = new AtomicBoolean(false);
        MailService mailService = spy(this.mailService);
        Mockito.doAnswer(invocationOnMock -> {
            wasExecuted.set(true);
            fromResult[0] = ((InternetAddress) (((Message) invocationOnMock.getArgument(0)).getFrom()[0])).getAddress();
            return null;
        }).when(mailService).send(ArgumentMatchers.any());
        mailService.sendEmail("test@testdomain.com");
        Assertions.assertTrue(wasExecuted.get());
        Assertions.assertEquals(properties.getProperty("fromEmail"), fromResult[0]);
    }

    @Test
    void wrongAddress() {
        Assertions.assertThrows(Exception.class, () -> {
            mailService.sendEmail("its_not_valid_email");
        });
    }


}
