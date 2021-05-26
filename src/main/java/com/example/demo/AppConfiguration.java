package com.example.demo;

import com.mysql.cj.jdbc.MysqlDataSource;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.util.Properties;

import static org.apache.logging.log4j.util.Strings.isEmpty;
@Log4j2
@Configuration
@PropertySource("classpath:application.properties")
public class AppConfiguration {

    @Bean
    public Properties applicationProperties() {
        Resource resource = new ClassPathResource("/application.properties");
        try {
            Properties props = PropertiesLoaderUtils.loadProperties(resource);
            return props;
        } catch (Exception e) {
            throw new RuntimeException("Application properties not found please put " +
                    "applicattion.properties file in resources folder");
        }
    }

    @Profile("prod")
    @Bean
    public DataSource prodDataSource(Properties applicationProperties) {
        log.info("initializing datasource prod database...");
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setPassword(applicationProperties.getProperty("database_password"));
        dataSource.setUser(applicationProperties.getProperty("database_user"));
        dataSource.setURL(applicationProperties.getProperty("database_url"));
        return dataSource;
    }

    @Profile("uat")
    @Bean
    public DataSource uatDataSource() {
        log.info("initializing datasource uat database...");
        String password = System.getProperty("database_password");
        String user = System.getProperty("database_user");
        String ip = System.getProperty("database_ip");
        String port = System.getProperty("database_port");

        if (isEmpty(password) || isEmpty(user) || isEmpty(ip) || isEmpty(port)) {
            throw new RuntimeException("password, user, ip, port has to be passed as ENVIRONMENT VARIABLE!");
        }

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setPassword(password);
        dataSource.setUser(user);
        dataSource.setURL("jdbc:mysql://" + ip + ":" + port + "/?useUnicode=true&serverTimezone=UTC");
        return dataSource;
    }

    @Profile("test")
    @Bean
    public DataSource embeddedDataSource() {
        log.info("initializing datasource h2 database...");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:hr;DB_CLOSE_DELAY=-1;MODE=MYSQL;DATABASE_TO_UPPER=false");
        dataSource.setUsername("sa");
        dataSource.setPassword("sa");

        // schema init
        Resource initSchema = new ClassPathResource("scripts/schema-h2.sql");
        DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema);
        DatabasePopulatorUtils.execute(databasePopulator, dataSource);

        return dataSource;
    }

    @Profile({"test", "prod","uat"})
    @Bean("emailProperties")
    public Properties emailProperties(Properties applicationProperties) {
        Properties properties = new Properties();

        properties.put("mail.smtp.host", applicationProperties.getProperty("mail.smtp.host"));
        properties.put("mail.smtp.port", applicationProperties.getProperty("mail.smtp.port"));
        properties.put("mail.smtp.starttls.enable", applicationProperties.getProperty("mail.smtp.starttls.enable"));
        properties.put("mail.smtp.auth", applicationProperties.getProperty("mail.smtp.auth"));
        properties.put("fromEmail", applicationProperties.getProperty("fromEmail"));
        properties.put("appPassword", applicationProperties.getProperty("appPassword"));

        return properties;
    }

}
