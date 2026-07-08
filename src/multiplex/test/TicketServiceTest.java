package multiplex.test;

import multiplex.serviceclasses.TicketService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.collections.Sets;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertFalse;


//Validates the core business logic for booking and canceling tickets.
//Mockito is used to simulate database insertions and deletions.
public class TicketServiceTest {

    //Mocking the JDBC API interfaces to prevent actual database modifications.
    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockStatement;

    //The service instance being tested
    private TicketService service;

    //Sets up the testing environment before each test method runs.
    //Initializes mocks and performs dependency injection.
    @BeforeEach
    public void setUp() {
        //Initializes the objects annotated with @Mock
        MockitoAnnotations.openMocks(this);

        //Dependency Injection: Pass the mocked connection into the service
        service = new TicketService(mockConnection);
    }

    @Test
    public void testBookTicket() throws SQLException {

        //Instruct the mocked connection to return our mocked statement for any SQL query.
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        
        //ARRANGE
        //Simulate a successful database insertion where exactly 1 row is affected.
        when(mockStatement.executeUpdate()).thenReturn(1);
        
        //ACT
        //Execute the method under test with dummy ticket data.
        boolean isBooked = service.bookTicket("A2", 12.5, 12, 1);
        
        //ASSERT
        //Verify that the service correctly interprets the successful DB insert as a successful booking.
        assertTrue(isBooked, "Ticket purchase failed!");
    }

    @Test
    public void testCancelTicket_InvalidId() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        
        //ARRANGE
        //Simulate a scenario where the deletion fails (e.g., ticket ID not found in the DB).
        when(mockStatement.executeUpdate()).thenReturn(0);

        //ACT
        //Attempt to cancel a ticket ID that does not exist.
        boolean isCanceled = service.cancelTicket(9999);

        //ASSERT
        //Verify that the cancellation logic correctly interprets the 0 affected rows as a failure.
        assertFalse(isCanceled, "Canceling a non-existent ticket should return false.");
    }
}