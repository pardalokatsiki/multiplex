package multiplex.serviceclasses;

import multiplex.dbconnection.DBConnection;
import multiplex.dataclasses.User;
import java.sql.*;

public class UserService {

    private Connection connection;

    private static User loggedInUser = null;

    public UserService() {
        this.connection = DBConnection.getConnection(); //establishing a connection to the database using the DBConnection class
    }

    //Returns the logged in user
    public static User getLoggedInUser(){
        return loggedInUser;
    }

    //Logout and memory clean out
    public static void logout() {
        loggedInUser=null;
        System.out.println("User logged out succesfully.");
    }

    //Method to register a new user, this method will insert a new user into the Users table in the database and return true if the insertion was successful, otherwise it will return false
    public boolean registerUser(String username, String password, String email) {
        String query = "INSERT INTO Users (username, password, email) VALUES (?, ?, ?)"; //SQL query to insert a new user into the Users table, using ? to prevent SQL injection
        
        try (PreparedStatement statement = connection.prepareStatement(query)) { //using try-with-resources to automatically close the PreparedStatement
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);
            
            int rowsAffected = statement.executeUpdate(); //execute the update and get the number of rows affected
            return rowsAffected > 0; //return true if at least one row was affected, the user was successfully registered
            
        } catch (SQLException e) {
            System.out.println("Registration error: " + e.getMessage());
            return false;
        }
    }

    //Method to login, this method will check if the provided username and password match a user in the Users table and return a User object if successful, otherwise it will return null
    public User loginUser(String username, String password) {
        String query = "SELECT * FROM Users WHERE username = ? AND password = ?"; //SQL query to select a user from the Users table based on the username and password
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) { //if a user is found, create and return a User object
                loggedInUser=new User(
                    resultSet.getInt("id"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("email")
                );

                return loggedInUser;
            }
            
        } catch (SQLException e) {
            System.out.println("Authentication error: " + e.getMessage());
        }
        return null; //return null if the user is not found or an error occurs
    }
}
