package com.example.archiveservice.db.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "hotEntityManagerFactory",
        transactionManagerRef = "hotTransactionManager",
        basePackages = {"com.example.archiveservice.db.hot.repository"})
public class HotDatasourceConfig {
    @Primary
    @Bean(name = "hotDataSourceProperties")
    @ConfigurationProperties("spring.datasource.hot")
    public DataSourceProperties hotDataSourceProperties() {
        return new DataSourceProperties();
    }
    @Primary
    @Bean(name = "hotJpaProperties")
    @ConfigurationProperties("spring.jpa.hot")
    public JpaProperties hotJpaProperties() {
        return new JpaProperties();
    }
    @Primary
    @Bean(name = "hotDataSource")
    public DataSource hotDataSource(@Qualifier("hotDataSourceProperties") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().build();
    }

    @Primary
    @Bean(name = "hotEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean hotEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("hotDataSource") DataSource dataSource,
            @Qualifier("hotJpaProperties") JpaProperties jpaProperties) {
        return builder
                .dataSource(dataSource)
                .packages("com.example.archiveservice.db.hot.entity")
                .persistenceUnit("hotPU")
                .properties(jpaProperties.getProperties())
                .build();
    }

    @Primary
    @Bean(name = "hotTransactionManager")
    public PlatformTransactionManager hotTransactionManager(
            @Qualifier("hotEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
