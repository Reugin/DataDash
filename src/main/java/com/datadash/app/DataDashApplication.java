package com.datadash.app;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.datadash.configuration.Configuration;

@SpringBootApplication
public class DataDashApplication {
	
	private static Configuration config = Configuration.getInstance();

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(DataDashApplication.class);
	    Properties properties = new Properties();

	    properties.put("spring.data.mongodb.host", config.getDbHost());
	    properties.put("spring.data.mongodb.port", config.getDbPort());
	    properties.put("spring.data.mongodb.username", config.getDbUsername());
	    properties.put("spring.data.mongodb.password", config.getDbPassword());
	    properties.put("spring.data.mongodb.database", config.getDatabase());

	    application.setDefaultProperties(properties);
	    application.run(args);
	}

}
