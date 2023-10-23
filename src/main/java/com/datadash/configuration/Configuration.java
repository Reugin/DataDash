package com.datadash.configuration;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class Configuration {

	// db details
	private String dbHost;
	
	private String dbPort;
	
	private String dbUsername;
	
	private String dbPassword;

	private String database;

	static final private Integer lock = 0;

	static private Configuration config = Configuration.getInstance();

	private Configuration() {

	}

	static {
		try {
			// String configPath = System.getenv("CONFIG_FILE_PATH");
//           String configPath = System.getenv("CONFIG_FILE_PATH");
 			String configPath = "/home/abhishek/workspace/test/DataDash/config.yaml";
			System.out.println(configPath);
			File file = new File(configPath);
			ObjectMapper om = new ObjectMapper(new YAMLFactory());
			config = om.readValue(file, Configuration.class);
			if (config != null) {

			} else {
				System.exit(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Configuration getInstance() {
		try {
			synchronized (lock) {
				if (config == null) {
					config = new Configuration();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return config;
	}

	public String getDbHost() {
		return dbHost;
	}

	public void setDbHost(String dbHost) {
		this.dbHost = dbHost;
	}

	public String getDbPort() {
		return dbPort;
	}

	public void setDbPort(String dbPort) {
		this.dbPort = dbPort;
	}

	public String getDbUsername() {
		return dbUsername;
	}

	public void setDbUsername(String dbUsername) {
		this.dbUsername = dbUsername;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}
	
	
	

}


