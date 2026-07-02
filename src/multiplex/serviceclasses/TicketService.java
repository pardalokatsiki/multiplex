package multiplex.serviceclasses;

import multiplex.dbconnection.DBConnection;
import java.sql.*;

public class TicketService {
    
    private Connection connection;

    public TicketService() {
        this.connection = DBConnection.getConnection(); //establishing a connection to the database using the DBConnection class
    }
    
    //Purchase ticket method, this method will insert a new ticket into the Tickets table in the database and return true if the insertion was successful, otherwise it will return false
    public boolean bookTicket(String seatNo, double price, int movieId, int userId) {
        String query = "INSERT INTO Tickets (seat_no, price, movies_id, users_id) VALUES (?, ?, ?, ?)"; //SQL query to insert a new ticket into the Tickets table, using ? to prevent SQL injection
        
        try (PreparedStatement statement = connection.prepareStatement(query)) { //using try-with-resources to automatically close the PreparedStatement
            statement.setString(1, seatNo);
            statement.setDouble(2, price);
            statement.setInt(3, movieId);
            statement.setInt(4, userId);
            
            int rowsAffected = statement.executeUpdate(); //execute the update and get the number of rows affected
            return rowsAffected > 0; //return true if at least one row was affected, the ticket was successfully booked
            
        } catch (SQLException e) {
            System.out.println("Purchase ticket error: " + e.getMessage());
            return false;
        }
    }

    //Cancel ticket method, this method will delete a ticket from the Tickets table based on the ticket ID and return true if the deletion was successful, otherwise it will return false
    public boolean cancelTicket(int ticketId) {
        String query = "DELETE FROM Tickets WHERE id = ?"; //SQL query to delete a ticket from the Tickets table based on the ticket ID

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, ticketId);
            
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0; //return true if at least one row was affected, the ticket was successfully canceled
            
        } catch (SQLException e) {
            System.out.println("Cancel ticket error: " + e.getMessage());
            return false;
        }
    }
}
