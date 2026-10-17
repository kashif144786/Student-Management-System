package com.sms.util;

import java.sql.*;

public class DBConnection {
	public static Connection getConnection() {
		try {
			
			Connection con = DriverManager.getConnection(
					
					"jdbc:mysql://localhost:3306/sms_db",
					"root","your_password_here"
					
					);
			return con;
			
		} catch(Exception e) {
			
			System.out.println(e);
			
		}
		return null;
	}

}
