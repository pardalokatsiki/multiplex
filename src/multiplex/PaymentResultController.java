package multiplex;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import multiplex.dataclasses.User;

public class PaymentResultController {
    @FXML
    private Button logoutButton;

    @FXML
    private Button moreTicketsButton;

    @FXML
    private Label paymentMessage;

    @FXML
    private Label userName;

    @FXML
    public void browseAgainButton() {
        MultiplexController.switchScene(ViewScenes.BROWSE);
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
