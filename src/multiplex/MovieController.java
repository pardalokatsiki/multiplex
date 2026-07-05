package multiplex;

import multiplex.dataclasses.Movie;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;


public class MovieController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane imageBorder;

    @FXML
    private AnchorPane movieContainer;

    @FXML
    private ImageView moviePosterBrowse;

    @FXML
    private ToggleButton movieTitleButton;

    private Movie movie;
    
    // When user clicks the movieTitleButton
    public void setMovieData(Movie movie) {
        //Set movie to current movie
        this.movie = movie;
        // Set text of movie button to current movie
        movieTitleButton.setText(movie.getTitle());
        // Set movie poster art
        Image image = new Image(getClass().getResourceAsStream(movie.getImage()));
        moviePosterBrowse.setImage(image);
    }
}
