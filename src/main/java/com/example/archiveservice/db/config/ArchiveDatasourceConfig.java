package com.example.archiveservice.db.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "archiveEntityManagerFactory",
        transactionManagerRef = "archiveTransactionManager",
        basePackages = {"com.example.archiveservice.db.archive.repository"})
public class ArchiveDatasourceConfig {
    @Bean(name = "archiveDataSourceProperties")
    @ConfigurationProperties("spring.datasource.archive")
    public DataSourceProperties archiveDataSourceProperties() {
        return new DataSourceProperties();
    }
    @Bean(name = "archiveJpaProperties")
    @ConfigurationProperties("spring.jpa.archive")
    public JpaProperties archiveJpaProperties() {
        return new JpaProperties();
    }
    @Bean(name = "archiveDataSource")
    public DataSource archiveDataSource(@Qualifier("archiveDataSourceProperties") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().build();
    }
    @Bean(name = "archiveEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean archiveEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("archiveDataSource") DataSource dataSource,
            @Qualifier("archiveJpaProperties") JpaProperties jpaProperties) {
        return builder
                .dataSource(dataSource)
                .packages("com.example.archiveservice.db.archive.entity")
                .persistenceUnit("archivePU")
                .properties(jpaProperties.getProperties())
                .build();
    }

    @Bean(name = "archiveTransactionManager")
    public PlatformTransactionManager archiveTransactionManager(
            @Qualifier("archiveEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
