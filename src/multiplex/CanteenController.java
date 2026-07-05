package multiplex;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import multiplex.dataclasses.User;

public class CanteenController {
    @FXML
    private Button backButton4;

    @FXML
    private Button confirmItems;

    @FXML
    private Button logoutButton;

    @FXML
    private TilePane snackList;

    @FXML
    private Label totalAmount;

    @FXML
    private Label userName;

    @FXML
    public void continueButtonClick() {
        MultiplexController.switchScene(ViewScenes.PAYMENT);
    }

    @FXML
    public void backButtonClick() {
        MultiplexController.switchScene(ViewScenes.SEAT);
    }

    @FXML
    public void logoutButtonClick() {
        MultiplexController.switchScene(ViewScenes.LOGIN);
    }

    public void initialize() {
        // Set current user credentials
        User user = Session.getCurrentUser();
        userName.setText(user.getUsername());
    }
}
