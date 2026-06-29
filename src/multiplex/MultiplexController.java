package multiplex;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class MultiplexController {

    @FXML
    private TextField userField;
    @FXML
    private PasswordField passField;
    @FXML
    private Button loginButton;

    @FXML
    public void handleButtonClick(ActionEvent event) {
        String text = userField.getText();
        String pwd = passField.getText();
        System.out.println("Looking for user: " + text + " with password: " + pwd);
    }
}
