package com.swathi.mysqljdbc.testmysql;

import java.sql.SQLException;
import java.util.Scanner;

public class TestClient {

	public static void main(String[] args) {
		MySQLConnection mySQLConnection = new MySQLConnection();
		try {
			boolean isConnectionEstablished = mySQLConnection.createDatabaseConnection();

			if(isConnectionEstablished) {
				
				String getUsers = "select * from user";
				mySQLConnection.readData(getUsers);
				
				System.out.println("\nWould you like to add a new user : Y / N ?");
				Scanner sc = new Scanner(System.in);
				while("Y".equalsIgnoreCase(sc.next())) {
					System.out.println("Enter user name : ");
					String username = sc.next();

					System.out.println("Enter city : ");
					String city = sc.next();

					mySQLConnection.addUser(username, city);

					System.out.println("\nWould you like to add a new user : Y / N ?");
				}
				
				mySQLConnection.readData(getUsers);
				sc.close();
			}
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mySQLConnection.closeConnections();
		}
	}
}
