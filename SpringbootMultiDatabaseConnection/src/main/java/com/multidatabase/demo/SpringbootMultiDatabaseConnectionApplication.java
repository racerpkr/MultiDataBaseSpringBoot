package com.multidatabase.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class/*,JpaRepositoriesAutoConfiguration.class*/})
@ComponentScan(basePackages={"com.multidatabase"})
public class SpringbootMultiDatabaseConnectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMultiDatabaseConnectionApplication.class, args);
	}
}
