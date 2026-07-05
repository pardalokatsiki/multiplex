package multiplex;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import multiplex.dataclasses.User;

public class SelectTimeController {
    @FXML
    private Button backButton2;

    @FXML
    private Button confirmButton2;

    @FXML
    private Button logoutButton;

    @FXML
    private Label selectedMovieDesc;

    @FXML
    private ImageView selectedMoviePoster;

    @FXML
    private Label selectedMovieTitle;

    @FXML
    private VBox timeList;

    @FXML
    private Label userName;

    @FXML
    public void logoutButtonClick() {
        MultiplexController.switchScene(ViewScenes.LOGIN);
    }

    @FXML
    public void continueButtonClick() {
        MultiplexController.switchScene(ViewScenes.SEAT);
    }

    @FXML
    public void backButtonClick() {
        MultiplexController.switchScene(ViewScenes.DAY);
    }

    public void initialize() {
        // Set current user credentials
        User user = Session.getCurrentUser();
        userName.setText(user.getUsername());
    }
}
