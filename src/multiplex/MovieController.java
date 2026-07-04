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

    public void setMovieData(Movie movie) {
        this.movie = movie;
        movieTitleButton.setText(movie.getTitle());
        Image image = new Image(getClass().getResourceAsStream(movie.getImgPath()));
        moviePosterBrowse.setImage(image);
    }
}
