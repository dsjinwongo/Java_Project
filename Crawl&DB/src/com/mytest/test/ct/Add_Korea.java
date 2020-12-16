package com.mytest.test.ct;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mytest.test.ct.Insert_Korea;

public class Add_Korea {
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
				
				String sql ="SELECT name, rate, review from KOREA where name = ?";
				
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, store_name);
				
				rs = stmt.executeQuery();
				
				if (rs.next()) {
					do {
						float yogiyo_rate = rs.getFloat("rate");
						int yogiyo_review = rs.getInt("review");
						
						int new_review = yogiyo_review+review;
						float new_rate = ((yogiyo_rate*yogiyo_review)+(rate*review))/new_review;
						
						String sql2 = "UPDATE KOREA SET NAME = ?, RATE = ?, REVIEW = ? WHERE NAME = ?";
						
						stmt = conn.prepareStatement(sql2);
						stmt.setString(1, store_name);
						stmt.setFloat(2, new_rate);
						stmt.setInt(3, new_review);
						stmt.setString(4, store_name);
						
						stmt.executeUpdate();
					}while(rs.next());
				}
				else {
					Insert_Korea.main(store_name, rate, review);
				}
				
				conn.commit();
				conn.close();
				
				
		} catch (Exception e) {
			System.err.println("�����߻� : " + e);
		}	
	}
}


