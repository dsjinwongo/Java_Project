package com.mytest.test.ct;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mytest.test.ct.Insert_Snack;

public class Check_Snack_Store {
   public static void main(String store_name, float rate, int review, String address) {
      
      try {
         Class.forName("oracle.jdbc.driver.OracleDriver"); 
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      }
      
      try {
            Connection conn = DriverManager.getConnection(
                  "jdbc:oracle:thin:@localhost:1521:XE",
                  "SYSTEM", "1234"
                  );

            conn.setAutoCommit(false);
            PreparedStatement stmt = null;
            ResultSet rs = null;
            
            String sql ="SELECT name from SNACK where name = ?";
            
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, store_name);
            
            rs = stmt.executeQuery();
            
            if (rs.next()) {
               do {
                  String sql2 = "DELETE FROM SNACK where name = ?";
                  
                  stmt = conn.prepareStatement(sql2);
                  stmt.setString(1, store_name);
                  
                  stmt.executeUpdate();
                  
               }while(rs.next());
            }
            
            Insert_Snack.main(store_name, rate, review, address);
            
            conn.commit();
            conn.close();
            
            
      } catch (Exception e) {
         System.err.println("오류발생 : " + e);
      }   
   }
}
