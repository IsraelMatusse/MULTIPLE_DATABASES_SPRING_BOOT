package com.personalprojects.multipledatabases.config;

import com.personalprojects.multipledatabases.domains.user.repos.UserRepo;
import jakarta.persistence.EntityManagerFactory;
import org.flywaydb.core.Flyway;
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
        basePackageClasses = UserRepo.class,
        entityManagerFactoryRef = "userEntityManager",
        transactionManagerRef = "userTransactionManager")
public class UserDbConfig {

    @Bean
    @ConfigurationProperties(prefix = "user.datasource")
    public DataSource userDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean userEntityManager(
            EntityManagerFactoryBuilder builder,
            @Qualifier("userDataSource") DataSource userDataSource
    ) {
        return builder
                .dataSource(userDataSource)
                .packages("com.personalprojects.multipledatabases.domains.user.model")
                .build();

    }
    @Bean
    public PlatformTransactionManager userTransactionManager(
            @Qualifier("userEntityManager") EntityManagerFactory userEntityManager) {
        return new JpaTransactionManager(userEntityManager);
    }

    @Bean
    public Flyway flywayUserMigration(@Qualifier("userDataSource") DataSource dataSource) {
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:db/migrations/user")
                .baselineOnMigrate(true)
                .load();
        flyway.migrate();
        return flyway;
    }


}
