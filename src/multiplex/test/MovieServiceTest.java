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
import static org.mockito.Mockito.when;

public class MovieServiceTest {
    
    @Mock private Connection mockConnection;
    @Mock private PreparedStatement mockStatement;
    @Mock private ResultSet mockResultSet;
    
    private MovieService service;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        
        service = new MovieService(mockConnection);
    }

    @Test
    public void testGetAllMovies() throws SQLException {
        // Πρέπει να "κοροϊδέψουμε" τη βάση ότι βρήκε 1 ταινία για να περάσει το assertFalse(isEmpty)
        when(mockResultSet.next()).thenReturn(true, false); 
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("title")).thenReturn("a test movie");
        when(mockResultSet.getInt("duration_min")).thenReturn(120);
        when(mockResultSet.getString("info")).thenReturn("info");
        when(mockResultSet.getString("showday")).thenReturn("Monday");
        when(mockResultSet.getString("showtime")).thenReturn("20:00");

        List<Movie> movies = service.getAllMovies();

        assertNotNull(movies, "The movie list should not be null.");
        assertFalse(movies.isEmpty(), "The list should not be empty.");
    }

    @Test
    public void testSearchMovies_ExistingKeyword() throws SQLException {
        // Εξομοιώνουμε ότι βρέθηκε μια ταινία που περιέχει το 'a'
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("id")).thenReturn(2);
        when(mockResultSet.getString("title")).thenReturn("avatar");
        when(mockResultSet.getInt("duration_min")).thenReturn(160);
        when(mockResultSet.getString("info")).thenReturn("sci-fi");
        when(mockResultSet.getString("showday")).thenReturn("Tuesday");
        when(mockResultSet.getString("showtime")).thenReturn("18:00");

        List<Movie> movies = service.searchMovies("a");

        assertNotNull(movies);
        assertFalse(movies.isEmpty(), "Searching for 'a' should return results.");
        assertTrue(movies.get(0).getTitle().toLowerCase().contains("a"), "The title must contain 'a'.");
    }

    @Test
    public void testSearchMovies_NonExistingKeyword() throws SQLException {
        // Εδώ θέλουμε να είναι άδεια, άρα το next() επιστρέφει false κατευθείαν
        when(mockResultSet.next()).thenReturn(false);

        List<Movie> movies = service.searchMovies("Zebra999xyz");

        assertNotNull(movies);
        assertTrue(movies.isEmpty(), "Searching for a non-existent movie should return an empty list.");
    }
}