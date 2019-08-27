package com.comtrade.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDataBase {
	private volatile static ConnectionDataBase instance;
	private Connection connection;
	
	
	public Connection getConnection() {
		return connection;
	}
	private ConnectionDataBase() {
		LoadDriver();
	}
	private void LoadDriver() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static ConnectionDataBase getInstance() {
		 if (instance == null) {
	            synchronized(ConnectionDataBase.class) {
	                if (instance == null) {
	                	instance = new ConnectionDataBase();
	                }
	            }
	        }
		 return instance;
	}

	public void runTransaction() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/booking", "root", "");
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void confirmedTransaction() {
		try {
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void rollBackTransaction() {
		try {
			connection.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
