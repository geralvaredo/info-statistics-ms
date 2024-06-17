package com.stadistic.infostatisticsms.config.datasource;

import com.stadistic.infostatisticsms.entity.Person;
import com.stadistic.infostatisticsms.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses ={Person.class, PersonRepository.class},
        entityManagerFactoryRef = "statisticsEntityManagerFactory",
        transactionManagerRef = "statisticsTransactionManager"
)
public class StatisticDataSourceConfig {

    @Bean(name = "statisticsDataSourceProperties")
    @ConfigurationProperties("spring.datasource.statistics")
    public DataSourceProperties statisticsDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "statisticsDataSource")
    public DataSource statisticsDataSource() {
        return statisticsDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Primary
    @Bean(name = "statisticsEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean statisticsEntityManagerFactory(
            @Qualifier("statisticsDataSource") DataSource dataSource, EntityManagerFactoryBuilder builder
    ) {
        return builder
                .dataSource(dataSource)
                .packages(Person.class)
                .build();
    }

    @Bean
    public PlatformTransactionManager statisticsTransactionManager(
            @Qualifier("statisticsEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory
    ) {
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory.getObject()));
    }


}
