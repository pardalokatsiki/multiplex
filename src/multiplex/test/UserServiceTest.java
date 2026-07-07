package multiplex.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.spi.DirStateFactory.Result;

import multiplex.serviceclasses.MovieService;
import multiplex.serviceclasses.UserService;
import multiplex.dataclasses.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    @Mock
    private Connection mockConnection;
    @Mock
    private PreparedStatement mockStatement;
    @Mock
    private ResultSet mockResultSet;
    
    private UserService userService;

    // Arrange
    @BeforeEach
    public void setUp() {
        mockConnection = mock(Connection.class);
        mockStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);

        try {
            when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
            when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        userService = new UserService(mockConnection);
    }

    @Test
    public void testRegisterUser_UsernameTooLong() {
        //Act: provide a username with more than 18 characters
        String result = userService.registerUser("ThisUsernameIsWayTooLong123456", "Str0ngP@ss!", "test@test.com");

        //Assert: Check if the message contains the failure text
        assertTrue(result.contains("Invalid Username format"), "Must reject usernames with more than 18 characters.");
    }

    @Test
    public void testRegisterUser_NullUsername() {
        //Act: We provide a null username
        String result = userService.registerUser(null, "Str0ngP@ss!", "test@test.com");

        //Assert
        assertTrue(result.contains("Invalid Username format"), "Must reject a null username.");
    }

    @Test
    public void testRegisterUser_InvalidPassword_NoSymbol() {
        //Act: We provide a password missing a special symbol (e.g., ! or @)
        String result = userService.registerUser("ValidUser", "WeakPassword123", "test@test.com");

        //Assert
        assertTrue(result.contains("Invalid password format"), "Must reject a password that fails the Regex (missing symbol).");
    }

    @Test
    public void testRegisterUser_InvalidPassword_TooShort() {
        //Act: We provide a password shorter than 8 characters
        String result = userService.registerUser("ValidUser", "Sh0rt!", "test@test.com");

        //Assert
        assertTrue(result.contains("Invalid password format"), "Must reject a password with fewer than 8 characters.");
    }

    @Test
    public void testRegisterUser_InvalidEmail() {
        //Act: We provide an email without the '@' symbol
        String result = userService.registerUser("ValidUser", "Str0ngP@ss!", "testdomain.com");

        //Assert: Here we can check the exact String returned by the method
        assertEquals("Invalid email format.", result, "Must reject an email without the @ symbol.");
    }

    @Test
    public void testLoginUser_InvalidCredentials() {
        //Act: We try to login with credentials that do not exist in the database
        User user = userService.loginUser("GhostUser999", "WrongP@ss123!");

        //Assert: Since the login fails, the method should return null
        assertNull(user, "Login with invalid credentials should return null.");
    }

    @Test
    public void testLogout() {
        //Act: We call the static logout method
        UserService.logout();

        //Assert: We check if the static variable loggedInUser actually became null
        assertNull(UserService.getLoggedInUser(), "After logout, loggedInUser must be cleared (null).");
    }
}