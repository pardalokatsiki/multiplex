package multiplex;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import multiplex.dataclasses.User;

public class SelectDaysController {
    @FXML
    private Button backButton1;

    @FXML
    private Button confirmButton1;

    @FXML
    private VBox daysList;

    @FXML
    private Button logoutButton;

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
        MultiplexController.switchScene(ViewScenes.TIME);
    }

    @FXML
    public void backButtonClick() {
        MultiplexController.switchScene(ViewScenes.BROWSE);
    }

    public void initialize() {
        // Set current user credentials
        User user = Session.getCurrentUser();
        userName.setText(user.getUsername());
    }
}
