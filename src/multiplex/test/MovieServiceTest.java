package multiplex.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import multiplex.dataclasses.Movie;
import multiplex.serviceclasses.MovieService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//We use Mockito to isolate the service layer from the database layer.
//This ensures our tests do not require an active database connection.
public class MovieServiceTest {
    
    //Mocking the JDBC API interfaces to prevent actual network/database calls.
    @Mock private Connection mockConnection;
    @Mock private PreparedStatement mockStatement;
    @Mock private ResultSet mockResultSet;
    
    //The instance of the class we are actually testing
    private MovieService service;

    //The setUp method runs automatically before each individual @Test.
    @BeforeEach
    public void setUp() throws SQLException{
        
        MockitoAnnotations.openMocks(this); //Initializes variables annotated with @Mock
        
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement); //When the service asks the connection for a PreparedStatement, return our mockStatement.
        when(mockStatement.executeQuery()).thenReturn(mockResultSet); //When the service executes the query, return our mockResultSet.

        service = new MovieService(mockConnection); //Pass the mocked connection into our service
    }

    @Test
    public void testGetAllMovies() throws SQLException {
        //ARRANGE
        //Configure the mockResultSet to simulate a database returning exactly one row.
        //next() will return 'true' the first time (row exists), and 'false' the second time (end of results).
        when(mockResultSet.next()).thenReturn(true, false); 
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("title")).thenReturn("The Matrix");
        when(mockResultSet.getInt("duration_min")).thenReturn(120);
        when(mockResultSet.getString("info")).thenReturn("info");
        when(mockResultSet.getString("showday")).thenReturn("Monday");
        when(mockResultSet.getString("showtime")).thenReturn("20:00");

        //ACT
        //Execute the method under test
        List<Movie> movies = service.getAllMovies();

        //ASSERT
        //Verify that the service correctly processed the mocked database result
        assertNotNull(movies, "The movie list should not be null.");
        assertFalse(movies.isEmpty(), "The list should not be empty.");
    }

    @Test
    public void testSearchMovies_ExistingKeyword() throws SQLException {
        //ARRANGE
        //Simulate finding a movie that contains the target keyword ('a')
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("id")).thenReturn(2);
        when(mockResultSet.getString("title")).thenReturn("avatar");
        when(mockResultSet.getInt("duration_min")).thenReturn(160);
        when(mockResultSet.getString("info")).thenReturn("sci-fi");
        when(mockResultSet.getString("showday")).thenReturn("Tuesday");
        when(mockResultSet.getString("showtime")).thenReturn("18:00");

        //ACT
        List<Movie> movies = service.searchMovies("a");

        //ASSERT
        assertNotNull(movies);
        assertFalse(movies.isEmpty(), "Searching for 'a' should return results.");
        assertTrue(movies.get(0).getTitle().toLowerCase().contains("a"), "The title must contain 'a'.");
    }

    @Test
    public void testSearchMovies_NonExistingKeyword() throws SQLException {
        //ARRANGE
        //We expect an empty result for a non-existent keyword.
        when(mockResultSet.next()).thenReturn(false);

        //ACT
        List<Movie> movies = service.searchMovies("Zebra999xyz");

        //ASSERT
        assertNotNull(movies);
        assertTrue(movies.isEmpty(), "Searching for a non-existent movie should return an empty list.");
    }
}