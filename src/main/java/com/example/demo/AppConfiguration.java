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
        dataSource.setURL("jdbc:mysql://51.124.98.77:3306/hr?useUnicode=true&serverTimezone=UTC");
        return dataSource;
    }

    @Profile("test")
    @Bean("h2DataSouce")
    public DataSource embeddedDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1;MODE=MYSQL");
        dataSource.setUsername("sa");
        dataSource.setPassword("sa");

        // schema init
        Resource initSchema = new ClassPathResource("scripts/schema-h2.sql");
        DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema);
        DatabasePopulatorUtils.execute(databasePopulator, dataSource);

        return dataSource;
    }

    @Bean()
    public Properties emailProperties(){
        Properties properties = new Properties();

        properties.put("mail.smtp.host", "smtp.mail.yahoo.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.ssl.trust", "*");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("fromEmail", "testemailserver202165@yahoo.com");
        properties.put("appPassword", "liixhnqccckjjbva");
        return properties;
    }

}
