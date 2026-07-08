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


//Validates user registration logic, authentication (login), and session management (logout).
//Mockito is used to bypass actual database communication, ensuring isolated and fast tests.
public class UserServiceTest {
    
    //Mocking the database interaction layers
    @Mock private Connection mockConnection;
    @Mock private PreparedStatement mockStatement;
    @Mock private ResultSet mockResultSet;
    
    //The service instance being tested
    private UserService userService;

    @BeforeEach
    public void setUp() throws SQLException {
        //Initializes the objects annotated with @Mock. 
        //Essential for preventing NullPointerExceptions.
        MockitoAnnotations.openMocks(this);

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement); //Instruct the mocked connection to return our mocked statement when a query is prepared.
        when(mockStatement.executeQuery()).thenReturn(mockResultSet); //Instruct the mocked statement to return our mocked result set when executed.
        
        userService = new UserService(mockConnection); //Dependency Injection: Pass the mocked connection into the service.
        
        //Reset the static logged-in user state before each test.
        UserService.logout(); 
    }

    @Test
    public void testRegisterUser_UsernameTooLong() {
        //ACT
        //Attempt to register a user with a username exceeding the 18-character limit.
        String result = userService.registerUser("ThisUsernameIsWayTooLong123456", "Str0ngP@ss!", "test@test.com");

        //ASSERT
        //Verify that the service catches the validation error before ever reaching the database.
        assertTrue(result.contains("Invalid Username format"), "Must reject usernames with more than 18 characters.");
    }

    @Test
    public void testRegisterUser_NullUsername() {
        //ACT
        //Attempt to register a user with a null username.
        String result = userService.registerUser("", "Str0ngP@ss!", "test@test.com");

        //ASSERT
        assertTrue(result.contains("Invalid Username format"), "Must reject a null username.");
    }

    @Test
    public void testRegisterUser_InvalidPassword_NoSymbol() {
        //ACT
        //Attempt to register with a password that lacks a special character (fails regex).
        String result = userService.registerUser("ValidUser", "WeakPassword123", "test@test.com");

        //ASSERT
        assertTrue(result.contains("Invalid password format"), "Must reject a password that fails the Regex (missing symbol).");
    }

    @Test
    public void testRegisterUser_InvalidPassword_TooShort() {
        //ACT
        //Attempt to register with a password shorter than 8 characters.
        String result = userService.registerUser("ValidUser", "Sh0rt!", "test@test.com");

        //ASSERT
        assertTrue(result.contains("Invalid password format"), "Must reject a password with fewer than 8 characters.");
    }

    @Test
    public void testRegisterUser_InvalidEmail() {
        //ACT
        //Attempt to register with an email missing the '@' character.
        String result = userService.registerUser("ValidUser", "Str0ngP@ss!", "testdomain.com");

        //ASSERT
        assertEquals("Invalid email format.", result, "Must reject an email without the @ symbol.");
    }

    @Test
    public void testLoginUser_InvalidCredentials() throws SQLException {
        //RRANGE
        //By default, Mockito makes mockResultSet.next() return false.
        //This perfectly simulates a scenario where the database query finds no matching user.

        //ACT
        //Attempt to log in with credentials that "do not exist".
        User user = userService.loginUser("GhostUser999", "WrongP@ss123!");

        //ASSERT
        //Verify that the method safely returns null instead of crashing or returning an empty user.
        assertNull(user, "Login with invalid credentials should return null.");
    }

    @Test
    public void testLogout() {
        //ACT
        //Trigger the static logout method to clear the session.
        UserService.logout();

        //ASSERT
        //Verify that the static loggedInUser variable was correctly reset to null.
        assertNull(UserService.getLoggedInUser(), "After logout, loggedInUser must be cleared (null).");
    }
}