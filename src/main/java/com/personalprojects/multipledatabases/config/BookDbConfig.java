package com.personalprojects.multipledatabases.config;

import com.personalprojects.multipledatabases.domains.book.repos.BookRepo;
import jakarta.persistence.EntityManagerFactory;
import org.flywaydb.core.Flyway;
import org.slf4j.LoggerFactory;
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
import java.util.logging.Logger;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackageClasses = BookRepo.class,
        entityManagerFactoryRef = "bookEntityManager",
        transactionManagerRef = "bookTransactionManager")
public class BookDbConfig {


    @Bean
    @Primary
    @ConfigurationProperties(prefix = "book.datasource")
    public DataSource bookDataSource() {

        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean bookEntityManager(
            EntityManagerFactoryBuilder builder,
            @Qualifier("bookDataSource") DataSource bookDataSource
    ) {
        return builder
                .dataSource(bookDataSource)
                .packages("com.personalprojects.multipledatabases.domains.book.model")
                .build();
    }

    @Bean
    public PlatformTransactionManager bookTransactionManager(
            @Qualifier("bookEntityManager") EntityManagerFactory bookEntityManager) {
        return new JpaTransactionManager(bookEntityManager);
    }

    @Bean
    public Flyway flywayBookMigration(@Qualifier("bookDataSource") DataSource dataSource) {
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:db/migrations/book")
                .baselineOnMigrate(true)
                .load();
        flyway.migrate();
        return flyway;
    }
}
