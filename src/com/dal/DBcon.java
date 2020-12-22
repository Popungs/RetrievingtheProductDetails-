package com.dal;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBcon {
	
	Connection conn = null;
	public DBcon() {
	
		try {
		
		Class.forName("org.h2.Driver");
		conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", ""); // establish the
		} catch (Exception e ) {
			
		}
	
	}
	public Connection getConnection() {
		return this.conn;
	}
	
}
