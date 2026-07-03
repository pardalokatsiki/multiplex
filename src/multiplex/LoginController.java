package multiplex;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passField;

    @FXML
    private Hyperlink registerLink;

    @FXML
    private TextField userField;

    @FXML
    void loginButtonClick(ActionEvent event) {
        String text = userField.getText();
        String pwd = passField.getText();
        if (!text.isEmpty() && !pwd.isEmpty()) {
            // Move to Browse Page Scene
            MultiplexController.switchScene(ViewScenes.BROWSE);
        }
    }

    @FXML
    public void registerLinkClick(ActionEvent event) throws IOException {
        // Move to Register Page Scene
        MultiplexController.switchScene(ViewScenes.REGISTER);
    }
}
