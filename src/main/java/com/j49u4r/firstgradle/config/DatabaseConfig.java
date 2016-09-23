package com.j49u4r.firstgradle.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

    private final Logger logger = Logger.getLogger(this.getClass());
    private static final boolean CONVERT = true;

    @Autowired
    private Environment env;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;

    @Bean
    public DataSource dataSource() {
        try {
            return getComboPooledDataSource();
        } catch (IOException | SQLException | PropertyVetoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setPackagesToScan(env.getProperty("entitymanager.packagesToScan"));

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactory.setJpaVendorAdapter(vendorAdapter);

        Properties additionalProperties = new Properties();
        additionalProperties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        additionalProperties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        additionalProperties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        additionalProperties.put("hibernate.id.new_generator_mappings", env.getProperty("hibernate.id.new_generator_mappings"));
        entityManagerFactory.setJpaProperties(additionalProperties);

        return entityManagerFactory;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    private ComboPooledDataSource getComboPooledDataSource() throws IOException, SQLException, PropertyVetoException {
        logger.info("Inicializando el Pool de conecciones...");
        ComboPooledDataSource cpds = new ComboPooledDataSource();

        // Básicos del pool
        cpds.setDriverClass(env.getProperty("db.driver"));
        cpds.setJdbcUrl(env.getProperty("db.url"));
        cpds.setUser(env.getProperty("db.username"));
        cpds.setPassword(env.getProperty("db.password"));

        // Dimensión del pool
        cpds.setInitialPoolSize(5);
        cpds.setMinPoolSize(5);
        cpds.setMaxPoolSize(20);
        cpds.setAcquireIncrement(5);
        cpds.setMaxStatements(180);

        // Reintentos
        cpds.setAcquireRetryAttempts(30);
        cpds.setAcquireRetryDelay(1000);
        cpds.setBreakAfterAcquireFailure(false);

        // Refresca conecciones
        cpds.setMaxIdleTime(180);
        cpds.setMaxConnectionAge(10);

        // Timeouts
        cpds.setCheckoutTimeout(5000);
        cpds.setIdleConnectionTestPeriod(60);
        cpds.setTestConnectionOnCheckout(true);
        cpds.setPreferredTestQuery("SELECT 1");
        cpds.setTestConnectionOnCheckin(true);

        logger.info("Pool de conecciones Iniciado");
        return cpds;
    }
}
