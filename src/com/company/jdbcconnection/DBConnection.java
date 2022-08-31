package com.company.jdbcconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	public Connection connection;
	
	public DBConnection(String dbUrl, String userId, String pwd) throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		this.connection = DriverManager.getConnection(dbUrl, userId, pwd);
	}
	
	public Connection getConnection() {
		return this.connection;
	}
	
	public void closeConnection() throws SQLException {
		if(this.connection != null) {
			this.connection.close();
		}
	}
}
