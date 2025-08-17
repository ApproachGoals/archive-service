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
        entityManagerFactoryRef = "coldEntityManagerFactory",
        transactionManagerRef = "coldTransactionManager",
        basePackages = {"com.example.archiveservice.db.cold.repository"})
public class ColdDatasourceConfig {
    @Bean(name = "coldDataSourceProperties")
    @ConfigurationProperties("spring.datasource.cold")
    public DataSourceProperties coldDataSourceProperties() {
        return new DataSourceProperties();
    }
    @Bean(name = "coldJpaProperties")
    @ConfigurationProperties("spring.jpa.cold")
    public JpaProperties coldJpaProperties() {
        return new JpaProperties();
    }
    @Bean(name = "coldDataSource")
    public DataSource coldDataSource(@Qualifier("coldDataSourceProperties") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().build();
    }
    @Bean(name = "coldEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean coldEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("coldDataSource") DataSource dataSource,
            @Qualifier("coldJpaProperties") JpaProperties jpaProperties) {
        return builder
                .dataSource(dataSource)
                .packages("com.example.archiveservice.db.cold.entity")
                .persistenceUnit("coldPU")
                .properties(jpaProperties.getProperties())
                .build();
    }

    @Bean(name = "coldTransactionManager")
    public PlatformTransactionManager coldTransactionManager(
            @Qualifier("coldEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
