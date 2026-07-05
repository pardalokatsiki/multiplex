package multiplex;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import multiplex.serviceclasses.UserService;
import multiplex.dataclasses.User;

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
    private Label errorLabel;

    @FXML
    void loginButtonClick() {
        // Get username from username field
        String name = userField.getText();
        // Get password from password
        String pwd = passField.getText();

        // Username field shouldn't be empty and at most 18 characters long
        // Password field shouldn't be empty and at least 8 characters long
        if ((!name.isEmpty() && name.length() <= 18)
                && (!pwd.isEmpty() && pwd.length() >= 8)) {
            // Check if user exists in the database
            UserService check = new UserService();
            // returns user if succesful, null if not
            User user = check.loginUser(name, pwd);

            if (user != null)
                // Move to Browse Page Scene
                Session.setCurrentUser(user);
                MultiplexController.switchScene(ViewScenes.BROWSE);
            // user = null
            errorLabel.setText("Login failed. Username With That Password Not Found\n");
        } else {
            errorLabel.setText("Username Must Be 1-18 Characters Long.\n" +
            "Password Must Be At Least 8 Characters Long.");
        }
    }

    @FXML
    public void registerLinkClick() throws IOException {
        // Move to Register Page Scene
        MultiplexController.switchScene(ViewScenes.REGISTER);
    }
}
