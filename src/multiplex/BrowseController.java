package multiplex;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import multiplex.dataclasses.User;

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

    @FXML
    public void initialize() {
        User user = Session.getCurrentUser();
        userName.setText(user.getUsername());
    }

    @FXML
    public  void logoutButtonClick(ActionEvent logout) {
        MultiplexController.switchScene(ViewScenes.LOGIN);
    }
}
