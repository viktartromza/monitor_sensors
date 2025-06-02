package by.agsr.monitorsensors.configuration;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

import java.util.HashMap;

import static by.agsr.monitorsensors.configuration.liquibase.LiquiBaseHelper.springLiquibase;

@Configuration
@EnableJpaRepositories(
        basePackages = "by.agsr.monitorsensors.summaryrepository",
        entityManagerFactoryRef = "summaryEntityManager",
        transactionManagerRef = "summaryTransactionManager"
)
public class PersistenceSummaryConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean summaryEntityManager(Environment env) {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(summaryDataSource());
        em.setPackagesToScan(
                "by.agsr.monitorsensors.model.summaryEntity");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto",
                env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect",
                env.getProperty("hibernate.dialect"));
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    public PlatformTransactionManager summaryTransactionManager(Environment env) {

        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                summaryEntityManager(env).getObject());
        return transactionManager;
    }
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.summary")
    public DataSource summaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.summary.liquibase")
    public LiquibaseProperties secondaryLiquibaseProperties() {
        return new LiquibaseProperties();
    }

    @Bean
    public SpringLiquibase secondaryLiquibase() {
        return springLiquibase(summaryDataSource(), secondaryLiquibaseProperties());
    }
}
