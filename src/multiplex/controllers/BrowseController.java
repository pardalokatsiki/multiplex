package multiplex.controllers;

import java.util.ArrayList;
import java.util.List;
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
import multiplex.Session;
import multiplex.ViewScenes;
import multiplex.dataclasses.Movie;
import multiplex.dataclasses.User;
import multiplex.serviceclasses.MovieService;
import javafx.scene.layout.Region;

public class BrowseController {
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

    // List of available movies
    private List<Movie> movies = new ArrayList<>();
    // List of movies filtered by search input
    private List<Movie> search = new ArrayList<>();

    // When the search button is clicked
    @FXML
    public void searchButtonClick() {
        // Start the movie service to access dabase information
        MovieService service = new MovieService();
        // User input search
        String movieSearch = movieSearchField.getText();
        // Get movies based on search string
        search = service.searchMovies(movieSearch);
        if (movieSearch.equals(null)) {
            // If search bar is empty show all movies
            displayMovies(movies);
        } else {
            // For search input show findings 
            displayMovies(search);
        }
    }
    // When ticket button is clicked
    @FXML
    public void ticketButtonClick() {
        MultiplexController.switchScene(ViewScenes.DAY);
    }
    // When logout button is clicked
    @FXML
    public void logoutButtonClick() {
        MultiplexController.switchScene(ViewScenes.LOGIN);
    }
    // Initialize values for the page
    public void initialize() {
        // Set current user credentials
        User user = Session.getCurrentUser();
        userName.setText(user.getUsername());
    
        // Start MovieService and get all movies from the database
        MovieService service = new MovieService();
        movies = service.getAllMovies();

        // Set poster art for movies
        for (Movie movie : movies) {
            String imagePath = "/multiplex/ImagesGoHere/MoviePosters/" + movie.getId() + ".png";
            movie.setImage(imagePath);
        }
        // Movie shown on the left pane
        if (movies.size() > 0) {
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
    // Display list of available movies
    public void displayMovies(List<Movie> movieList) {
        // clear tilepane
        moviesList.getChildren().clear();
        try {
            for (Movie movie : movieList) {
                // Update image path manually
                String imagePath = "/multiplex/ImagesGoHere/MoviePosters/" + movie.getId() + ".png";
                movie.setImage(imagePath);

                // Create a loader for each movie poster
                FXMLLoader fxmlLoader = new FXMLLoader();
                // Set movie container 
                fxmlLoader.setLocation(getClass().getResource("/multiplex/fxml-files/Movie.fxml"));
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

    // Movie is clicked and shows up on the left pane
    public void setChosenMovie(Movie movie) {
        // Set movie title
        movieTitle.setText(movie.getTitle());
        // Set movie poster art
        var image = new Image(getClass().getResourceAsStream(movie.getImage()));
        moviePoster.setImage(image);
        // Set movie description
        movieDesc.setText(movie.getInfo());
        Session.setSelectedMovie(movie);
    }
    // Function for controllers to show the selected movie
    // on the left pane/side
    public static void selectedMoviePane(Label name, Label title, Label desc, ImageView poster) {
        // Set current user credentials
        User user = Session.getCurrentUser();
        name.setText(user.getUsername());
        // Set the previously selected movie
        Movie movie = Session.getSelectedMovie();
        // Set image poster
        var image = new Image(BrowseController.class.getResourceAsStream(movie.getImage()));
        poster.setImage(image);
        // Set movie title
        title.setText(movie.getTitle());
        // Set movie description
        desc.setText(movie.getInfo());
    }
}
