package com.swathi.mysqljdbc.testmysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.swathi.mysqljdbc.util.PropFileReader;

/**
 *  To connect to MySQL database using
 *  JDBC connection object
 * 
 * @author swath
 *
 */
public class MySQLConnection {

	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	private PreparedStatement preparedStatement;
	
	public boolean createDatabaseConnection() throws ClassNotFoundException{
		
		boolean isConnectionEstablished = false;
		String mysqlUsername = PropFileReader.readData(PropertyConstants.USERNAME.toString());
		String mySqlPwd = PropFileReader.readData(PropertyConstants.PASSWORD.toString());
		String driverName = PropFileReader.readData(PropertyConstants.JDBC_DRIVER.toString());
		String connectionUrl = PropFileReader.readData(PropertyConstants.CONNECTION_URL.toString());
		
		Class.forName(driverName);
		
		try {
			connection = DriverManager.getConnection(connectionUrl, mysqlUsername, mySqlPwd);
			isConnectionEstablished = true;
		} catch (SQLException e) {
			try {
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		return isConnectionEstablished;
	}
	
	public void closeConnections() {
		try {
			statement.close();
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void readData(String sql) throws SQLException {
		
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);
		
		System.out.println("Existing users in the database :");
		
		if(null != resultSet) {
			while(resultSet.next()) {
				System.out.print(resultSet.getInt(1) + "  |  ");
				System.out.print(resultSet.getString(2) + "  |  ");
				System.out.println(resultSet.getString(3));
			}
		}
	}
	
	public void addUser(String username, String city) throws SQLException {
		preparedStatement = connection.prepareStatement("insert into user values(default,?,?)");
		preparedStatement.setString(1, username);
		preparedStatement.setString(2, city);
		preparedStatement.executeUpdate();		
	}
}
