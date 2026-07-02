
package multiplex.serviceclasses;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieService {
    
        // Στοιχεία σύνδεσης (βάλτε τα σωστά για το περιβάλλον σας)
    private static final String DB_URL = "jdbc:mysql://localhost:3306/multiplex";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    private Connection connection;

    public MovieService() {
        try {
            // Φόρτωση του MySQL Driver για σιγουριά
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Σφάλμα σύνδεσης: " + e.getMessage());
        }
    }

    // Method to search for movies
    public List<Movies> searchMovies(String keyword) {
        List<Movies> moviesList = new ArrayList<>();
        String query = "SELECT * FROM movies WHERE title LIKE ?";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + keyword + "%");
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                // ΔΙΟΡΘΩΣΗ: Μετονομασία σε "movie" (ενικός) για να μην μπερδεύεται με τη λίστα
                Movies movie = new Movies(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getInt("duration_min"),
                    resultSet.getString("info"),
                    resultSet.getString("showday"),
                    resultSet.getString("showtime")
                );
                // Προσθήκη της μίας ταινίας στη λίστα
                moviesList.add(movie);
            }
            resultSet.close();

        } catch (SQLException e) {
            System.out.println("Search Error: " + e.getMessage());
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
        List<Movies> results = service.searchMovies("a");
        
        // 3. Εμφάνιση αποτελεσμάτων
        if (results.isEmpty()) {
            System.out.println("Δεν βρέθηκαν αποτελέσματα ή η βάση είναι άδεια.");
        } else {
            System.out.println("Βρέθηκαν " + results.size() + " ταινίες:");
            for (Movies m : results) {
                System.out.println("- " + m.getTitle() + " | Προβολή: " + m.getShowDate() + " " + m.getShowTime());
            }
        }
        System.out.println("--- Τέλος δοκιμής ---");
    }
}

    
    
    
}
