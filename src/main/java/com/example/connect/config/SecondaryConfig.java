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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
   basePackages = "com.example.connect.secondary_repo",
   entityManagerFactoryRef = "secondaryEntityManagerFactory",
   transactionManagerRef = "secondaryTransactionManager"
)
public class SecondaryConfig {

  @Bean(name = "secondaryDataSource")
  @ConfigurationProperties(prefix = "secondary.datasource")
  public DataSource dataSource() {
    return DataSourceBuilder.create().type(HikariDataSource.class).build();
  }


  @Bean(name = "secondaryEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory(
          @Qualifier("secondaryDataSource") DataSource secondaryDataSource,
          EntityManagerFactoryBuilder builder
  ) {
    return builder
            .dataSource(secondaryDataSource)
            .packages("com.example.connect.secondary_entity")
            .persistenceUnit("secondaryPU")
            .build();
  }


  @Bean(name = "secondaryTransactionManager")
  public PlatformTransactionManager transactionManager(
      @Qualifier("secondaryEntityManagerFactory")EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }


  @Bean(name = "secondaryLiquibase")
  public SpringLiquibase secondaryLiquibase(@Qualifier("secondaryDataSource") DataSource dataSource) {
    SpringLiquibase liquibase = new SpringLiquibase();
    liquibase.setDataSource(dataSource);
    liquibase.setChangeLog("classpath:db/changelog/changelog-master.xml");
    liquibase.setContexts("secondary");
    liquibase.setShouldRun(false); // no update = false
    return liquibase;
  }
}