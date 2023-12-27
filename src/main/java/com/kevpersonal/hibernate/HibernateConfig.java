package com.kevpersonal.hibernate;

import com.kevpersonal.exceptions.ApplicationConfigNotFoundException;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {

    @Autowired
    private Environment environment;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {

        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource());
        localSessionFactoryBean.setPackagesToScan(
                "com.kevpersonal.entity"
        );
        localSessionFactoryBean.setHibernateProperties(hibernateProperties());

        return localSessionFactoryBean;
    }

    @Bean
    public DataSource dataSource() {

        HikariDataSource dataSource = new HikariDataSource();

        Properties props = readConfigurationFileForProps(
                                String.format("application-%s.properties",
                                                environment.getProperty("env", "local")
                                )
                            );

        dataSource.setDriverClassName(
                props.getProperty("spring.datasource.driver-class-name", "org.h2.Driver")
        );

        dataSource.setUsername(
                props.getProperty("spring.datasource.username", "sa")
        );

        dataSource.setPassword(
                props.getProperty("spring.datasource.password", "sa")
        );

        dataSource.setJdbcUrl(
                props.getProperty("spring.datasource.url", "jdbc:h2:mem:db;DB_CLOSE_DELAY=-1")
        );

        return dataSource;
    }

    private Properties readConfigurationFileForProps(String resourceFileName) throws ApplicationConfigNotFoundException
    {
        Properties props = new Properties();
        ClassLoader clazz = Thread.currentThread().getContextClassLoader();

        try(InputStream is = clazz.getResourceAsStream(resourceFileName)) {
            props.load(is);
        } catch (java.io.IOException exp) {
            throw new ApplicationConfigNotFoundException(
                    String.format(
                            "Resource file application-%s.properties Not Found",
                            environment.getProperty("env", "local")
                    )
            );
        }

        return props;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {

        HibernateTransactionManager transactionManager = new HibernateTransactionManager();

        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;

    }

    private final Properties hibernateProperties() {

        Properties hibernateProperties = readConfigurationFileForProps(
                                                    String.format("application-%s.properties",
                                                            environment.getProperty("env", "local")
                                                    )
                                            );

        hibernateProperties.setProperty(
                "hibernate.hbm2ddl.auto", hibernateProperties.getProperty("", "create-drop")
        );
        hibernateProperties.setProperty(
                "hibernate.dialect", hibernateProperties.getProperty("", "org.hibernate.dialect.H2Dialect")
        );

        return hibernateProperties;
    }
}
