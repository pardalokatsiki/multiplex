package multiplex.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import multiplex.serviceclasses.TicketService;

public class TicketServiceTest {
 
    @Test
    public void testBookTicket() {
        // 1. Προετοιμασία (Arrange): Φτιάχνουμε το αντικείμενο του service μας
        TicketService service = new TicketService();
        
        // 2. Εκτέλεση (Act): Καλούμε την πραγματική σας μέθοδο με κάποια δοκιμαστικά δεδομένα
        // (Θέση: "A1", Τιμή: 12.5, movieId: 1, userId: 1)
        boolean isBooked = service.bookTicket("A1", 12.5, 1, 1);
        
        // 3. Έλεγχος (Assert): Ελέγχουμε αν η μέθοδος επέστρεψε true (δηλαδή αν πέτυχε η αγορά)
        assertTrue(isBooked, "Η αγορά του εισιτηρίου απέτυχε!");
    }
}