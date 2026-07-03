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
        //The "WHERE id = ?" acts as a built in safeguard.
        //Since 'id' is a Primary Key, this query will strictly delete at most one specific row.
        //There is no risk of accidentally deleting all records from the Tickets table so there is no need to add a confirmation step before executing the deletion.

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, ticketId);
            
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0; //return true if at least one row was affected, the ticket was successfully canceled
            
        } catch (SQLException e) {
            System.out.println("Cancel ticket error: " + e.getMessage());
            return false;
        }
    }

    // --- TEMPORARY TEST CODE ---
    public static void main(String[] args) {
        System.out.println("--- Starting TicketService Test ---");

        TicketService ticketService = new TicketService();

        // 1. Test Booking a Ticket
        System.out.println("\n[1] Testing Ticket Booking...");
        // Using existing User ID (1 = Mary) and Movie ID (12 = The Godfather)
        boolean bookSuccess = ticketService.bookTicket("VIP-1", 15.00, 12, 1);
        
        if (bookSuccess) {
            System.out.println("Result: SUCCESS! Ticket booked for Seat VIP-1.");
        } else {
            System.out.println("Result: FAILED to book ticket. (Check database connection or foreign keys).");
        }

        // 2. Test Canceling an Existing Ticket
        System.out.println("\n[2] Testing Ticket Cancellation (Existing Ticket)...");
        // From our SQL script, we know ticket ID 2 (Mary's original ticket) exists.
        boolean cancelSuccess = ticketService.cancelTicket(2);
        
        if (cancelSuccess) {
            System.out.println("Result: SUCCESS! Ticket ID 2 was successfully canceled/deleted.");
        } else {
            System.out.println("Result: FAILED. Ticket ID 2 might not exist (maybe you already deleted it!).");
        }

        // 3. Test Canceling a Non-Existent Ticket (Edge Case)
        System.out.println("\n[3] Testing Ticket Cancellation (Fake Ticket)...");
        boolean cancelFail = ticketService.cancelTicket(9999);
        
        if (!cancelFail) {
            System.out.println("Result: SUCCESS (Expected). Ticket ID 9999 does not exist, so the method correctly returned false.");
        } else {
            System.out.println("Result: ERROR! It deleted something that shouldn't exist!");
        }

        System.out.println("\n--- Test Complete ---");
    }
}
