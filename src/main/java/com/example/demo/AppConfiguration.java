package com.example.demo;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

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
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setPassword(applicationProperties.getProperty("database_password"));
        dataSource.setUser(applicationProperties.getProperty("database_user"));
        dataSource.setURL(applicationProperties.getProperty("database_url"));
        return dataSource;
    }

    @Profile("test")
    @Bean("h2DataSouce")
    public DataSource embeddedDataSource() {
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

    @Profile({"test", "prod"})
    @Bean("emailProperties")
    public Properties emailProperties(Properties applicationProperties) throws IOException {
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
