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
        
        // Username πεδίο πρέπει να μην είναι κενό και το πολύ 18 χαρακτήρες
        // Password πεδίο πρέπει να μην είναι κενό και το λιγότερο 8 χαρακτήρες
        // Email πεδίο πρέπει να περιέχει το σύμβολο @
        if ( (!username.isEmpty() && password.length() <= 18) 
            && (!password.isEmpty() && password.length() >= 8)
            && (!email.isEmpty() && email.length() >= 8 && email.contains("@")) )
        //Move to Login Page Scene
        MultiplexController.switchScene(ViewScenes.LOGIN);
    }

    @FXML
    void goBackButtonClick(ActionEvent goBack) {
        MultiplexController.switchScene(ViewScenes.LOGIN);
    }
}
