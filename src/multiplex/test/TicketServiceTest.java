package multiplex.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import multiplex.serviceclasses.TicketService;

public class TicketServiceTest {
 
    private TicketService service;

    //Arrange: Runs automatically before each @Test
    @BeforeEach
    public void setUp() {
        service = new TicketService();
    }

    @Test
    public void testBookTicket() {
        //Act: We use an EXISTING movie (e.g., movieId = 12)
        boolean isBooked = service.bookTicket("A2", 12.5, 12, 1);
        
        //Assert: Check if the method returned true
        assertTrue(isBooked, "Ticket purchase failed! (Check the IDs or if the seat is already taken)");
    }

    @Test
    public void testCancelTicket_InvalidId() {
        //Act: We try to cancel a ticket that does not exist (ID: 9999)
        boolean isCanceled = service.cancelTicket(9999);

        //Assert: We expect the cancellation to fail (false)
        assertFalse(isCanceled, "Canceling a non-existent ticket should return false.");
    }
}