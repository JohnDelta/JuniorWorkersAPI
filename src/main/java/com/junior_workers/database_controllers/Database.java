package com.junior_workers.database_controllers;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

	private String hostName = "localhost";
	private String databaseName = "junior_workers";
	private String username = "juniorworkers_user";
	private String password = "juniorworkers_password";
	
	private Connection connection = null;
	
	public Connection getConnection() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			connection = DriverManager
                    .getConnection("jdbc:mysql://"+hostName+"/"+databaseName+""
                    		+ "?user="+username
                    		+ "&password="+password
                    		+ "&useUnicode=true"
                    		+ "&characterEncoding=UTF8"
                    		+ "&sessionVariables=default_storage_engine=InnoDB"
                    		+ "&serverTimezone=UTC");
			
		} catch(Exception e) {
			System.out.println("Error in Database.java | Exception message: " + e.getStackTrace());
		}
		
		return connection;
	}
	
}





