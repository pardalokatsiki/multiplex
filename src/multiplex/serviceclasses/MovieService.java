package multiplex.serviceclasses;

import multiplex.dataclasses.Movie;
import multiplex.dbconnection.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieService {

    private Connection connection;

    public MovieService() {
        this.connection = DBConnection.getConnection(); //establishing a connection to the database using the DBConnection class
    }

    //Method to search for movies, this method will search for movies in the database that match the given keyword in their title and return a list of Movie objects that match the search criteria
    public List<Movie> searchMovies(String keyword) {
        List<Movie> moviesList = new ArrayList<>();
        String query = "SELECT * FROM movies WHERE title LIKE ?"; //SQL query to search for movies by title using ? to prevent SQL injection
        
        try (PreparedStatement statement = connection.prepareStatement(query)) { //using try-with-resources to automatically close the PreparedStatement
            statement.setString(1, "%" + keyword + "%"); //using % for wildcard search in SQL
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) { //looking for all the movies that match the search criteria
                Movie movie = new Movie (
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getInt("duration_min"),
                    resultSet.getString("info"),
                    resultSet.getString("showday"),
                    resultSet.getString("showtime")
                );
                moviesList.add(movie); //adding the movie to the list of movies that match the search criteria
            }
            resultSet.close(); //closing the ResultSet to free up resources

        } catch (SQLException e) {
            System.out.println("Search Error: " + e.getMessage());
        }
        return moviesList;
    }

    //Method to get all movies, this method will retrieve all movies from the database and return a list of Movie objects
    public List<Movie> getAllMovies() {
        List<Movie> moviesList = new ArrayList<>();
        String query = "SELECT * FROM movies"; //SQL query to get all movies
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) { //looking for all the movies in the database
                Movie movie = new Movie (
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getInt("duration_min"),
                    resultSet.getString("info"),
                    resultSet.getString("showday"),
                    resultSet.getString("showtime")
                );
                moviesList.add(movie); //adding the movie to the list of all movies
            }
            resultSet.close();

        } catch (SQLException e) {
            System.out.println("Browse Error: " + e.getMessage());
        }
        return moviesList;
    }

    // --- ΠΡΟΣΩΡΙΝΟΣ ΚΩΔΙΚΑΣ ΓΙΑ ΔΟΚΙΜΗ (TESTING) ---
    public static void main(String[] args) {
        System.out.println("--- Έναρξη δοκιμής Backend ---");

        // 1. Δημιουργία του Service
        MovieService service = new MovieService();

        // 2. Εκτέλεση δοκιμαστικής αναζήτησης
        System.out.println("Γίνεται αναζήτηση στη βάση για ταινίες που περιέχουν το 'a'...");
        List<Movie> results = service.searchMovies("a");

        // 3. Εμφάνιση αποτελεσμάτων
        if (results.isEmpty()) {
            System.out.println("Δεν βρέθηκαν αποτελέσματα ή η βάση είναι άδεια.");
        } else {
            System.out.println("Βρέθηκαν " + results.size() + " ταινίες:");
            for (Movie m : results) {
                System.out.println("- " + m.getTitle() + " | Προβολή: " + m.getShowDay() + " " + m.getShowTime());
            }
        }
        System.out.println("--- Τέλος δοκιμής ---");
    }

}

    
    
    
