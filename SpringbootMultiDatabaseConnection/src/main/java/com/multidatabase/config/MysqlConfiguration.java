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
		entityManagerFactoryRef = "mysqlEntityManager", 
		transactionManagerRef = "mysqlTransactionManager", 
		basePackages = "com.multidatabase.mysql.repository"
)
public class MysqlConfiguration {    
	    
	    @Bean(name = "mySqlDataSourceProperties")
	    @ConfigurationProperties(prefix = "spring.mysql.datasource")
	    public DataSourceProperties mySqlDataSourceProperties(){
	    	return new DataSourceProperties();
	    }
	    
		@Bean(name = "mysqldataSource")
		@ConfigurationProperties(prefix = "spring.mysql.datasource")
		public DataSource dataSource(@Qualifier("mySqlDataSourceProperties") DataSourceProperties dataSourceProperties) {
			return dataSourceProperties.initializeDataSourceBuilder().build();
		}

		@PersistenceContext(unitName="mysql")
		@Bean(name = "mysqlEntityManager")
		public LocalContainerEntityManagerFactoryBean mysqlentityManagerFactory(EntityManagerFactoryBuilder builder,
				@Qualifier("mysqldataSource") DataSource dataSource) {
			return builder.dataSource(dataSource).packages("com.multidatabase.mysql.entity")
					.persistenceUnit("mysql").build();
		}

		@Bean(name = "mysqlTransactionManager")
		public PlatformTransactionManager transactionManager(
				@Qualifier("mysqlEntityManager") EntityManagerFactory entityManagerFactory) {
			return new JpaTransactionManager(entityManagerFactory);
		} 
	    
}
