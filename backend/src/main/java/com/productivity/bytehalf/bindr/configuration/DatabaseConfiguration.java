package com.productivity.bytehalf.bindr.configuration;

import com.productivity.bytehalf.bindr.util.user.DetermineOS;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

/**
 * The type Database configuration.
 */
@Configuration
@PropertySource(value = {"classpath:application.properties"})
public class DatabaseConfiguration {

    private static String url = "";
    private Logger logger = LoggerFactory.getLogger(DatabaseConfiguration.class);

    /**
     * Gets url.
     *
     * @return the database url
     */
    public static String getUrl() {
        return url;
    }

    /**
     * Sets url.
     *
     * @param url the database url
     */
    public static void setUrl(String url) {
        DatabaseConfiguration.url = url;
    }

    /**
     * Bindr db data source.
     *
     * @return the data source
     * @throws IOException the io exception
     */
    @Primary
    @Bean
    public DataSource BindrDB() throws IOException {
        ClassLoader loader = PropertiesUtil.class.getClassLoader();

        String applicationProperties = Objects.requireNonNull(loader.getResource(
                "application.properties")).getPath();

        Properties prop = new Properties();

        prop.load(new FileInputStream(applicationProperties));

        String driverClassName = prop.getProperty("driver");

        String connectionName = prop.getProperty("connection.name");
        String appDirectory = prop.getProperty("bindr.app.directory");
        String fileName = prop.getProperty("bindr.app.database.name");

        DetermineOS determineOS = new DetermineOS();

        switch (determineOS.getOS()) {
            case WINDOWS:

                String osAppDataDirectory = System.getenv("APPDATA").replaceAll("\\\\",
                        "/");
                setUrl(connectionName + osAppDataDirectory + appDirectory + fileName);
                logger.info(getUrl());

                break;
            case LINUX:

                osAppDataDirectory = System.getProperty("user.home");
                setUrl(connectionName + osAppDataDirectory + appDirectory + fileName);
                logger.info(getUrl());

                break;
            case MAC:

                osAppDataDirectory = System.getProperty("user.home");
                setUrl(connectionName + osAppDataDirectory + "/Library/Application " +
                        "Support/" + appDirectory + fileName);
                logger.info(getUrl());

                break;
            default:
                logger.info("Unknown Operating System");
        }

        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        DriverManagerDataSource source = new DriverManagerDataSource();

        source.setDriverClassName(driverClassName);
        source.setUrl(getUrl());
        source.setUsername(username);
        source.setPassword(password);

        return source;
    }
}
