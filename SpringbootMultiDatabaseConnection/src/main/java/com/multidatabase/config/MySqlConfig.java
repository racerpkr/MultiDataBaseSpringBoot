package com.multidatabase.config;

import org.springframework.beans.factory.annotation.Value;

/*import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

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

*//**
 * 
 * @author praveenkumar
 *
 *//*
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "mysqlentityManagerFactory",
transactionManagerRef = "mysqltransactionManager", basePackages = {
		"com.multidatabase.mysql.repository" })
public class MySqlConfig {

	@Bean(name = "mysqldataSource")
	@ConfigurationProperties(prefix = "mysql.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@PersistenceContext(unitName="mysql")
	@Bean(name = "mysqlentityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("mysqldataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.multidatabase.mysql.entity")
				.persistenceUnit("mysql").build();
	}

	@Bean(name = "mysqltransactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("mysqlentityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
	
	  private Properties hibernateProperties() {
	        final Properties hibernateProperties = new Properties();
	        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
	        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
	        return hibernateProperties;
	    }

}


*/

/*

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(
       basePackages = {"com.multidatabase.mysql.repository"},
       entityManagerFactoryRef = "entityManagerFactory1", 
       transactionManagerRef = "transactionManager1")
@EnableTransactionManagement
public class MySqlConfig {

	
	@Value("${mysql.datasource.driver-class-name}")
	String mySqlDriverName;
	
	@Value("${mysql.datasource.url}")
	String dataSourceUrl;
	
	@Value("${mysql.datasource.username}")
	String userName;
	
	@Value("${mysql.datasource.password}")
	String password;
	
	
	
	
   @Bean(name = "transactionManager1")
   public PlatformTransactionManager transactionManager1() {
       return new JpaTransactionManager(entityManagerFactory1().getObject());
   }

   @Bean(name = "entityManagerFactory1")

   public LocalContainerEntityManagerFactoryBean entityManagerFactory1() {

       HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();

       LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
       factoryBean.setDataSource(mySqlDataSource());
       factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
       factoryBean.setJpaProperties(hibernateProperties());

       factoryBean.setPackagesToScan("com.multidatabase.mysql.repository");
       factoryBean.setPersistenceUnitName("mysql");

       return factoryBean;
   }

  
   @Bean
   public DataSource mySqlDataSource(){
	   
	   DriverManagerDataSource dataSource = new DriverManagerDataSource();
	   dataSource.setUrl(dataSourceUrl);
	   dataSource.setUsername(userName);
	   dataSource.setPassword(password);
	   dataSource.setDriverClassName(mySqlDriverName);
	   
	   return dataSource;
	   
   }
   
   
   @Bean(name = "dataSource1")
  // @ConfigurationProperties(prefix = "datasource1")
   @ConfigurationProperties(prefix = "mysql.datasource")
   public DataSource dataSource1() {
       return DataSourceBuilder
               .create()
               .build();
   }

   private Properties hibernateProperties() {
       final Properties hibernateProperties = new Properties();
       hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
       hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
       return hibernateProperties;
   }
}*/