package multiplex.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import multiplex.serviceclasses.UserService;
import multiplex.dataclasses.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    
    @Mock private Connection mockConnection;
    @Mock private PreparedStatement mockStatement;
    @Mock private ResultSet mockResultSet;
    
    private UserService userService;

    @BeforeEach
    public void setUp() throws SQLException {
        // ΑΥΤΟ ΕΛΕΙΠΕ ΚΑΙ ΕΣΠΑΓΕ ΤΑ ΠΑΝΤΑ:
        MockitoAnnotations.openMocks(this);

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        
        userService = new UserService(mockConnection);
        
        // Καθαρίζουμε τον loggedInUser πριν από κάθε τεστ
        UserService.logout(); 
    }

    @Test
    public void testRegisterUser_UsernameTooLong() {
        String result = userService.registerUser("ThisUsernameIsWayTooLong123456", "Str0ngP@ss!", "test@test.com");
        assertTrue(result.contains("Invalid Username format"), "Must reject usernames with more than 18 characters.");
    }

    @Test
    public void testRegisterUser_NullUsername() {
        String result = userService.registerUser(null, "Str0ngP@ss!", "test@test.com");
        assertTrue(result.contains("Invalid Username format"), "Must reject a null username.");
    }

    @Test
    public void testRegisterUser_InvalidPassword_NoSymbol() {
        String result = userService.registerUser("ValidUser", "WeakPassword123", "test@test.com");
        assertTrue(result.contains("Invalid password format"), "Must reject a password that fails the Regex (missing symbol).");
    }

    @Test
    public void testRegisterUser_InvalidPassword_TooShort() {
        String result = userService.registerUser("ValidUser", "Sh0rt!", "test@test.com");
        assertTrue(result.contains("Invalid password format"), "Must reject a password with fewer than 8 characters.");
    }

    @Test
    public void testRegisterUser_InvalidEmail() {
        String result = userService.registerUser("ValidUser", "Str0ngP@ss!", "testdomain.com");
        assertEquals("Invalid email format.", result, "Must reject an email without the @ symbol.");
    }

    @Test
    public void testLoginUser_InvalidCredentials() throws SQLException {
        // By default το mockResultSet.next() επιστρέφει false, άρα το login αποτυγχάνει σωστά.
        User user = userService.loginUser("GhostUser999", "WrongP@ss123!");
        assertNull(user, "Login with invalid credentials should return null.");
    }

    @Test
    public void testLogout() {
        UserService.logout();
        assertNull(UserService.getLoggedInUser(), "After logout, loggedInUser must be cleared (null).");
    }
}