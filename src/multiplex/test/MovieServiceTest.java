package multiplex.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

import multiplex.dataclasses.Movie;
import multiplex.serviceclasses.MovieService; // Προστέθηκε αυτό για να "βλέπει" το Service

public class MovieServiceTest {

    @Test
    public void testGetAllMovies() {
        MovieService service = new MovieService();
        List<Movie> movies = service.getAllMovies();

        assertNotNull(movies, "Η λίστα των ταινιών δεν πρέπει να είναι null.");
        assertFalse(movies.isEmpty(), "Η λίστα δεν πρέπει να είναι άδεια.");
    }

    @Test
    public void testSearchMovies_ExistingKeyword() {
        MovieService service = new MovieService();
        List<Movie> movies = service.searchMovies("a");

        assertNotNull(movies);
        assertFalse(movies.isEmpty(), "Η αναζήτηση για 'a' θα έπρεπε να επιστρέψει αποτελέσματα.");
    }

    @Test
    public void testSearchMovies_NonExistingKeyword() {
        MovieService service = new MovieService();
        List<Movie> movies = service.searchMovies("Zebra999xyz");

        assertNotNull(movies);
        assertTrue(movies.isEmpty(), "Η αναζήτηση για ανύπαρκτη ταινία πρέπει να επιστρέφει άδεια λίστα.");
    }
}