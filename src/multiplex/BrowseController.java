package multiplex;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import multiplex.dataclasses.Movie;
import multiplex.dataclasses.User;
import multiplex.serviceclasses.MovieService;

public class BrowseController {
    @FXML
    private GridPane grid;

    @FXML
    private Button logoutButton;

    @FXML
    private Label movieDesc;

    @FXML
    private ImageView moviePoster;

    @FXML
    private Label movieTitle;

    @FXML
    private Button searchButton;

    @FXML
    private Button ticketButton;

    @FXML
    private Label userName;

    private List<Movie> movies = new ArrayList<>();

    @FXML
    public void initialize() {
        // Set current user credentials
        User user = Session.getCurrentUser();
        userName.setText(user.getUsername());
        // Start MovieService and get all movies from the database
        MovieService service = new MovieService();
        movies = service.getAllMovies();
    }

    @FXML
    public  void logoutButtonClick(ActionEvent logout) {
        MultiplexController.switchScene(ViewScenes.LOGIN);
    }
}
