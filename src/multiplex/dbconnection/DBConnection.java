package multiplex.dbconnection;

import java.sql.*;

public class DBConnection {
    
    // Below variables are needed for getConnection method
    
    private static final String url = "jdbc:mysql://localhost:3306/multiplex"; 
    // The creation of the url follows this format: protocol//[hosts][/database][?properties], from mysql quide
    // 3306 is for xammp
    private static final String username = "root";
    private static final String passwd = "";
    
    public static Connection getConnection(){ 
        // method connecting sql to java code 
        // with static we don't need to create any BDConnection objects 
      try{
          
        Connection con = DriverManager.getConnection(url,username,passwd); // DriverManager provides url to installed jdbc driver
        System.out.println("Connected"); 
        return con;
          
      }catch(SQLException ex){  // handle various possible errors 
        System.out.println("Not Connected");
        System.out.println("SQLException: " + ex.getMessage());
        System.out.println("SQLState: " + ex.getSQLState());
      }
     
      return null;
    
    }     
    
}
