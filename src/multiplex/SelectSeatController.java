package multiplex;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import multiplex.dataclasses.User;

public class SelectSeatController {
    @FXML
    private Button backButton3;

    @FXML
    private Button confirmButton3;

    @FXML
    private Button logoutButton;

    @FXML
    private TilePane seatList;

    @FXML
    private Label selectedMovieDesc;

    @FXML
    private ImageView selectedMoviePoster;

    @FXML
    private Label selectedMovieTitle;

    @FXML
    private Label userName;

    @FXML
    public void logoutButtonClick() {
        MultiplexController.switchScene(ViewScenes.LOGIN);
    }

    @FXML
    public void continueButtonClick() {
        System.out.println("Purchase Successful");
    }

    @FXML
    public void backButtonClick() {
        MultiplexController.switchScene(ViewScenes.TIME);
    }

    public void initialize() {
        // Set current user credentials
        User user = Session.getCurrentUser();
        userName.setText(user.getUsername());
    }
}
