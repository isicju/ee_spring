package com.example.demo;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class AppConfiguration  {

    @Profile("prod")
    @Bean
    public DataSource prodDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setPassword("secret_password");
        dataSource.setUser("root");
        dataSource.setURL("jdbc:mysql://51.124.98.77:3306/?useUnicode=true&serverTimezone=UTC");
        return dataSource;
    }

    @Profile({"test","prod"})
    @Bean("emailProperties")
    public Properties emailProperties(){
        Properties properties = new Properties();

        properties.put("mail.smtp.host", "smtp.mail.yahoo.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("fromEmail", "emailforserver2021@yahoo.com");
        properties.put("appPassword", "ykbhpninutedzvdl");

        return properties;
    }

}
