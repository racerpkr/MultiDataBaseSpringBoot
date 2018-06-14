
/*package com.multidatabase.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.multidatabase.postgres.repository.IUsers;

*//**
 * 
 * @author praveenkumar
 *
 *//*
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory",
transactionManagerRef = "transactionManager", basePackages = {
		"com.multidatabase.postgres.repository" })
public class PostgresConfig {

	
	@Value("${spring.datasource.driver-class-name}")
	String driverClassName;
	
	@Value("${spring.datasource.url}")
	String dataSourceUrl;
	
	@Value("${spring.datasource.username}")
	String userName;
	
	@Value("${spring.datasource.password}")
	String password;
	
	
	@Value("${hibernate.show_sql}")
	Object showSql;
	
	//@Autowired JpaVendorAdapter jpaVendorAdopter;
	
	//@Autowired DataSource dataSource;
	
	@Primary
	@Bean(name = "dataSource")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Primary
	   @Bean(name = "dataSource")
	   public DataSource mySqlDataSource(){
		   
		   DriverManagerDataSource dataSource = new DriverManagerDataSource();
		   dataSource.setUrl(dataSourceUrl);
		   dataSource.setUsername(userName);
		   dataSource.setPassword(password);
		   dataSource.setDriverClassName(driverClassName);
		   
		   return dataSource;
		   
	   }
	
	@PersistenceContext(unitName = "postgres")
	@Primary
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("dataSource") DataSource dataSource) {
		
		//HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		
		LocalContainerEntityManagerFactoryBean localEntity = new LocalContainerEntityManagerFactoryBean();
		localEntity.setDataSource(dataSource);
		localEntity.setPackagesToScan("com.multidatabase.postgres.entity");
		localEntity.setPersistenceUnitName("postgres");
	//	localEntity.setJpaVendorAdapter(jpaVendorAdopter);
		//localEntity.setJpaProperties(hibernateProperties());
		localEntity.afterPropertiesSet();
		
		return localEntity;
	//	return builder.dataSource(dataSource).properties(hibernateProperties()).packages(IUsers.class).persistenceUnit("postgres").build();
	}

	@Primary
	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
	
	
	private Map<String, Object> hibernateProperties() {

		Resource resource = new ClassPathResource("hibernate.properties");
		
		try {
			Properties properties = PropertiesLoaderUtils.loadProperties(resource);
			return properties.entrySet().stream()
											.collect(Collectors.toMap(
														e -> e.getKey().toString(),
														e -> e.getValue())
													);
		} catch (IOException e) {
			return new HashMap<String, Object>();
		}
}
	
	   private Properties hibernateProperties() {
	       final Properties hibernateProperties = new Properties();
	       hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL94Dialect");
	       hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
	       return hibernateProperties;
	   }

}





@Configuration
@EnableTransactionManagement
@EnableAutoConfiguration
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager",
        basePackages = {"com.mysource.repository"})
public class RepositoryConfig {
    @Autowired
    JpaVendorAdapter jpaVendorAdapter;

    @Autowired
    DataSource dataSource;

    @Bean(name = "entityManager")
    public EntityManager entityManager() {
        return entityManagerFactory().createEntityManager();
    }

    @Primary
    @Bean(name = "entityManagerFactory")
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setJpaVendorAdapter(jpaVendorAdapter);
        emf.setPackagesToScan("com.mysource.model");
        emf.setPersistenceUnitName("default");   // <- giving 'default' as name
        emf.afterPropertiesSet();
        return emf.getObject();
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager tm = new JpaTransactionManager();
        tm.setEntityManagerFactory(entityManagerFactory());
        return tm;
    }
    
    
    
    
    
    	@Value("${spring.postgres.datasource.driver-class-name}")
	String driverClassName;
	
	@Value("${spring.postgres.datasource.url}")
	String dataSourceUrl;
	
	@Value("${spring.postgres.datasource.username}")
	String userName;
	
	@Value("${spring.postgres.datasource.password}")
	String password;
	
	@Value("${hibernate.dialect.postgres}")
	Object hibernateDialect;
	
	@Value("${hibernate.show_sql}")
	Object hibernateShowSql;
	
	@Value("${hibernate.format_sql}")
	Object hibernateFormatSql;
	
	@Value("${hibernate.hbm2ddl.auto}")
	Object hibernateHbmAuto;
	
//
//
//	@Primary
//	   @Bean(name = "dataSource")
//	   public DataSource postgresqlDataSource(){
//		   
//		   DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		   dataSource.setUrl(dataSourceUrl);
//		   dataSource.setUsername(userName);
//		   dataSource.setPassword(password);
//		   dataSource.setDriverClassName(driverClassName);
//		   
//		   
//		   return dataSource;
//		   
//	   }
//
//	
//
//	/**
//	 * Entity manager definition. 
//	 *  
//	 * @param builder an EntityManagerFactoryBuilder.
//	 * @return LocalContainerEntityManagerFactoryBean.
//	 */
//	@PersistenceContext(unitName = "postgresql")
//	@Primary
//	@Bean(name = "postgresqlEntityManager")
//	public LocalContainerEntityManagerFactoryBean postgresqlEntityManagerFactory(EntityManagerFactoryBuilder builder) {
//		return builder
//					.dataSource(postgresqlDataSource())
//					.properties(hibernateProperties())
//					.packages(Users.class)
//					.persistenceUnit("postgresql")
//					.build();
//	}
//
//	@Primary
//	@Bean(name = "postgresqlTransactionManager")
//	public PlatformTransactionManager postgresqlTransactionManager(@Qualifier("postgresqlEntityManager") EntityManagerFactory entityManagerFactory) {
//		return new JpaTransactionManager(entityManagerFactory);
//	}
//
//	private Map<String, Object> hibernateProperties() {
//
//		Map<String, Object> hibernateProp = new HashMap<>();
//		Object hibernateDialectValue = "org.hibernate.dialect.PostgreSQL94Dialect";
//		Object up= "update";
//		hibernateProp.put("hibernate.show_sql", hibernateShowSql);
//		hibernateProp.put("hibernate.format_sql", hibernateFormatSql);
//		hibernateProp.put("hibernate.hbm2ddl.auto", hibernateHbmAuto);
//		hibernateProp.put("hibernate.dialect", hibernateDialect);
//		return hibernateProp;
//
//	}
//	
//	
	


/*	@Primary
	   @Bean(name = "dataSource")
	   public DataSource mySqlDataSource(){
		   
		   DriverManagerDataSource dataSource = new DriverManagerDataSource();
		   dataSource.setUrl(dataSourceUrl);
		   dataSource.setUsername(userName);
		   dataSource.setPassword(password);
		   dataSource.setDriverClassName(driverClassName);
		   
		   return dataSource;
		   
	   }*/

