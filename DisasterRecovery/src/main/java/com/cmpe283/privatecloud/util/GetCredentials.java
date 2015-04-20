package com.cmpe283.privatecloud.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



public class GetCredentials {

	private static String userName;
	private static String password;
	private static String database;
	
	private static GetCredentials credentials;
	
	private GetCredentials(){
		
	}
	
	public static  GetCredentials getInstance(){
		credentials=new GetCredentials();
		return credentials ;
	}
	
	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public void setCredentials() throws IOException{
		
		Properties properties=new Properties();
		
		String propertyFileName="application.properties";
		
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertyFileName);
		
		if (inputStream != null) {
			properties.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + propertyFileName + "' not found in the classpath");
		}
		
		
		setUserName(properties.getProperty("userName"));
		setPassword(properties.getProperty("password"));
		setDatabase(properties.getProperty("database"));
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
