package multiplex;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CreateUserController {
    @FXML
    private Button backToLoginButton;

    @FXML
    private Button createAccountButton;

    @FXML
    private TextField createEmail;

    @FXML
    private TextField createPass;

    @FXML
    private TextField createUsername;

    @FXML
    void createAccountClick(ActionEvent event) {
        String username = createUsername.getText();
        String password = createPass.getText();
        String email = createEmail.getText();
        
        //TODO Add SQL query to save user credentials
        
        //Move to Login Page Scene
        MultiplexController.switchScene(ViewScenes.LOGIN);
    }

    @FXML
    void goBackButtonClick(ActionEvent goBack) {
        MultiplexController.switchScene(ViewScenes.LOGIN);
    }
}
