package multiplex.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

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

public class MovieServiceTest {
    // Mock the Java SQL Connection
    @Mock
    private Connection mockConnection;
    // Mock the SQL Statement 
    @Mock
    private PreparedStatement mockStatement;
    @Mock
    private ResultSet mockResultSet;
    
    //Declare the service at the class level
    private MovieService service;

    //This method runs automatically before each @Test!
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
        service = new MovieService(mockConnection);
    }

    @Test
    public void testGetAllMovies() {
        //ACT
        List<Movie> movies = service.getAllMovies();

        //ASSERT: Check the results
        assertNotNull(movies, "The movie list should not be null.");
        assertFalse(movies.isEmpty(), "The list should not be empty.");
    }

    @Test
    public void testSearchMovies_ExistingKeyword() {
        //ACT
        List<Movie> movies = service.searchMovies("a");

        //ASSERT
        assertNotNull(movies);
        assertFalse(movies.isEmpty(), "Searching for 'a' should return results.");
        assertTrue(movies.get(0).getTitle().toLowerCase().contains("a"), "The title must contain 'a'.");
    }

    @Test
    public void testSearchMovies_NonExistingKeyword() {
        //ACT
        List<Movie> movies = service.searchMovies("Zebra999xyz");

        //ASSERT
        assertNotNull(movies);
        assertTrue(movies.isEmpty(), "Searching for a non-existent movie should return an empty list.");
    }
}