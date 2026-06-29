package multiplex;

import java.sql.*;

public class MultiplexModel {
    public static void main(String[] args) {
        Connection connection = null;
        
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
    
            //TODO: Create Database and Replace mydb With Its Name
            //TODO: Replace mydbuser with Username and Password
            
            // Establish Connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "mydbuser", "mydbuser");
        
            // Statement to execute queries
            Statement statement = connection.createStatement();
            
            //TODO: Add a query to get username and password for login
            ResultSet resultSet = statement.executeQuery(null);

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}