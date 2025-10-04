package com.example.connect.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
   basePackages = "com.example.connect.primary_repo",
   entityManagerFactoryRef = "primaryEntityManagerFactory",
   transactionManagerRef = "primaryTransactionManager"
)
public class PrimaryConfig {

  @Bean(name = "primaryDataSource")
  @Primary
  @ConfigurationProperties(prefix = "primary.datasource")
  public DataSource dataSource() {
    return DataSourceBuilder.create().type(HikariDataSource.class).build();
  }


  @Bean(name = "primaryEntityManagerFactory")
  @Primary
  public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(
     @Qualifier("primaryDataSource") DataSource primaryDataSource,
     EntityManagerFactoryBuilder builder

  ) {
    return builder
            .dataSource(primaryDataSource)
            .packages("com.example.connect.primary_entity")
            .persistenceUnit("primaryPU")
            .build();
  }


  @Bean(name = "primaryTransactionManager")
  @Primary
  public PlatformTransactionManager transactionManager(
    @Qualifier("primaryEntityManagerFactory")EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }


  @Bean(name = "primaryLiquibase")
  @Primary
  public SpringLiquibase primaryLiquibase(@Qualifier("primaryDataSource") DataSource dataSource) {
    SpringLiquibase liquibase = new SpringLiquibase();
    liquibase.setDataSource(dataSource);
    liquibase.setChangeLog("classpath:db/changelog/changelog-master.xml");
    liquibase.setContexts("primary");
    liquibase.setShouldRun(false); // no update = false
    return liquibase;
  }
}