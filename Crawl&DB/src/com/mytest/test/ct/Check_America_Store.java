package com.mytest.test.ct;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mytest.test.ct.Insert_America;

public class Check_America_Store {
	public static void main(String store_name, float rate, int review) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
				Connection conn = DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521:XE",
						"C##abc", "1234"
						);

				conn.setAutoCommit(false);
				PreparedStatement stmt = null;
				ResultSet rs = null;
				
				String sql ="SELECT name from AMERICA where name = ?";
				
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, store_name);
				
				rs = stmt.executeQuery();
				
				if (rs.next()) {
					do {
						String sql2 = "DELETE FROM AMERICA where name = ?";
						
						stmt = conn.prepareStatement(sql2);
						stmt.setString(1, store_name);
						
						stmt.executeUpdate();
						
					}while(rs.next());
				}
				
				Insert_America.main(store_name, rate, review);
				
				conn.commit();
				conn.close();
				
				
		} catch (Exception e) {
			System.err.println("�����߻� : " + e);
		}	
	}
}

