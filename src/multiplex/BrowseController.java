package multiplex;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import multiplex.dataclasses.Movie;
import multiplex.dataclasses.User;
import multiplex.serviceclasses.MovieService;
import javafx.scene.layout.Region;

public class BrowseController implements Initializable {
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
    private TilePane moviesContainer;

    @FXML
    private Button searchButton;

    @FXML
    private Button ticketButton;

    @FXML
    private Label userName;

    private List<Movie> movies = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set current user credentials
        User user = Session.getCurrentUser();
        userName.setText(user.getUsername());

        // Start MovieService and get all movies from the database
        MovieService service = new MovieService();
        movies = service.getAllMovies();

        
        // TODO: Add code for movie posters
        for(Movie movie : movies) {
            String imagePath = "ImagesGoHere/MoviePosters/" + movie.getId() + ".png";
            movie.setImage(imagePath);
            System.out.println(imagePath);
        }
 
        if(movies.size() > 0) {
            setChosenMovie(movies.get(0));
        }

        moviesContainer.setMinWidth(Region.USE_COMPUTED_SIZE);
        moviesContainer.setPrefWidth(Region.USE_COMPUTED_SIZE);
        moviesContainer.setMaxWidth(Region.USE_PREF_SIZE);
        //set tilepane height
        moviesContainer.setMinHeight(Region.USE_COMPUTED_SIZE);
        moviesContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        moviesContainer.setMaxHeight(Region.USE_PREF_SIZE);

        try {
            for(Movie movie : movies) {
                // Create a loader for each movie poster
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("fxml-files/Movie.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                MovieController movieController = fxmlLoader.getController();
                movieController.setMovieData(movie);

                moviesContainer.getChildren().add(anchorPane);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
    // Method for when a user clicks on a movie
    @FXML
    public void setChosenMovie(Movie movie) {
        // Set movie title
        movieTitle.setText(movie.getTitle());
        // Set movie poster art
        var image = new Image(getClass().getResourceAsStream(movie.getImage()));
        moviePoster.setImage(image);
        // Set movie description
        movieDesc.setText(movie.getInfo());
    }

    @FXML
    public  void logoutButtonClick(ActionEvent logout) {
        MultiplexController.switchScene(ViewScenes.LOGIN);
    }
}
