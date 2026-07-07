package multiplex.test;

import multiplex.serviceclasses.TicketService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TicketServiceTest {

    // (Change 'DatabaseConnection' to whatever class your service actually uses to talk to the DB)
    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockStatement;

    private TicketService service;

    // Arrange: Runs automatically before each @Test
    @BeforeEach
    public void setUp() {
        // This replaces 'service = new TicketService();'
        MockitoAnnotations.openMocks(this);

        service = new TicketService(mockConnection);
    }

    @Test
    public void testBookTicket() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        
        when(mockStatement.executeUpdate()).thenReturn(1);
        
        boolean isBooked = service.bookTicket("A2", 12.5, 12, 1);
        
        assertTrue(isBooked, "Ticket purchase failed!");
    }

    @Test
    public void testCancelTicket_InvalidId() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        
        when(mockStatement.executeUpdate()).thenReturn(0);

        boolean isCanceled = service.cancelTicket(9999);

        //Assert: We expect the cancellation to fail (false)
        assertFalse(isCanceled, "Canceling a non-existent ticket should return false.");
    }
}