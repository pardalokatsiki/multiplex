package multiplex;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class MultiplexController {

    @FXML
    private Label cinemaLogo;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passField;

    @FXML
    private Hyperlink registerLink;

    @FXML
    private Text registerText;

    @FXML
    private TextField userField;

    @FXML
    public void loginButtonClick(ActionEvent event) {
        String text = userField.getText();
        String pwd = passField.getText();
        if(!text.isEmpty() && !pwd.isEmpty())
            System.out.println("Looking for user: " + text + " with password: " + pwd);
    }

    @FXML
    public void registerLinkClick(ActionEvent event) {
        //Move to Register Page Scene
    }
}
