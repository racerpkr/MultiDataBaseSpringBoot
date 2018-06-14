package com.multidatabase.config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

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



/**
 * 
 * @author praveenkumar
 *
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "postgresqlEntityManager", 
		transactionManagerRef = "postgresqlTransactionManager", 
		basePackages = "com.multidatabase.postgres.repository"
)
public class PostgresqlConfiguration {
		
	
    @Bean(name = "postgresDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.postgres.datasource")
    public DataSourceProperties postgresDataSourceProperties(){
    	return new DataSourceProperties();
    }
	
	@Primary
	@Bean(name = "dataSource")
	@ConfigurationProperties(prefix = "spring.postgres.datasource")
	public DataSource dataSource(@Qualifier("postgresDataSourceProperties") DataSourceProperties postgresDataSource) {
		return postgresDataSource.initializeDataSourceBuilder().build();
		//return DataSourceBuilder.create().build();
	}
	
	@PersistenceContext(unitName = "postgres")
	@Primary
	@Bean(name = "postgresqlEntityManager")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("dataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.multidatabase.postgres.entity").persistenceUnit("postgres").build();
	}

	@Primary
	@Bean(name = "postgresqlTransactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("postgresqlEntityManager") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

	
	
}
