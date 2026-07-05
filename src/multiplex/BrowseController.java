package multiplex;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import multiplex.dataclasses.Movie;
import multiplex.dataclasses.User;
import multiplex.serviceclasses.MovieService;
import javafx.scene.layout.Region;

public class BrowseController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button logoutButton;

    @FXML
    private Label movieDesc;

    @FXML
    private ImageView moviePoster;

    @FXML
    private Label movieTitle;

    @FXML
    private TilePane moviesList;

    @FXML
    private Button searchButton;

    @FXML
    private TextField movieSearchField;

    @FXML
    private Button ticketButton;

    @FXML
    private Label userName;

    private List<Movie> movies = new ArrayList<>();
    private List<Movie> search = new ArrayList<>();

    public void initialize() {
        // Set current user credentials
        User user = Session.getCurrentUser();
        userName.setText(user.getUsername());

        // Start MovieService and get all movies from the database
        MovieService service = new MovieService();
        movies = service.getAllMovies();

        // Set poster art for movies
        for(Movie movie : movies) {
            String imagePath = "ImagesGoHere/MoviePosters/" + movie.getId() + ".png";
            movie.setImage(imagePath);
        }

        if(movies.size() > 0) {
            setChosenMovie(movies.get(0));
        }

        // Set Tilepane Width
        moviesList.setMinWidth(Region.USE_COMPUTED_SIZE);
        moviesList.setPrefWidth(Region.USE_COMPUTED_SIZE);
        moviesList.setMaxWidth(Region.USE_PREF_SIZE);
        // Set tilepane height
        moviesList.setMinHeight(Region.USE_COMPUTED_SIZE);
        moviesList.setPrefHeight(Region.USE_COMPUTED_SIZE);
        moviesList.setMaxHeight(Region.USE_PREF_SIZE);

        displayMovies(movies);
    }

    public void displayMovies(List<Movie> movieList) {
        // clear tilepane
        moviesList.getChildren().clear();

        try {
            for(Movie movie : movieList) {

                // Update image path manually
                String imagePath = "ImagesGoHere/MoviePosters/" + movie.getId() + ".png";
                movie.setImage(imagePath);
                
                // Create a loader for each movie poster
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("fxml-files/Movie.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                MovieController movieController = fxmlLoader.getController();
                movieController.setMovieData(movie);
                movieController.setOnSelected(this::setChosenMovie);
                moviesList.getChildren().add(anchorPane);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    // Method for when a user clicks on a movie
    public void setChosenMovie(Movie movie) {
        // Set movie title
        movieTitle.setText(movie.getTitle());
        // Set movie poster art
        var image = new Image(getClass().getResourceAsStream(movie.getImage()));
        moviePoster.setImage(image);
        // Set movie description
        movieDesc.setText(movie.getInfo());
    }

    // When user clicks the search button
    @FXML
    public void searchButtonClick() {
        MovieService service = new MovieService();
        String movieSearch = movieSearchField.getText();
        
        search = service.searchMovies(movieSearch);

        if(movieSearch.equals(null)) {
            displayMovies(movies);
        } else {
            displayMovies(search);
        }
    }

    @FXML
    public  void logoutButtonClick() {
        MultiplexController.switchScene(ViewScenes.LOGIN);
    }
}
