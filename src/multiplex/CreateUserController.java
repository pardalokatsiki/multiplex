package multiplex;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import multiplex.serviceclasses.UserService;

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
    private Label errorLabel;   

    @FXML
    void createAccountClick(ActionEvent event) {
        String username = createUsername.getText();
        String password = createPass.getText();
        String email = createEmail.getText();
        
        // Register user in database
        UserService service = new UserService();
        String message = service.registerUser(username, password, email);
        
        if(message.equals("User created.")) {
            //Move to Login Page Scene
            MultiplexController.switchScene(ViewScenes.LOGIN);
        } else {
            // Show error message if there are problems in the input fields 
            // TODO fix errorLabel not showing the entire text
            errorLabel.setText(message);
        }

    }

    @FXML
    void goBackButtonClick(ActionEvent goBack) {
        MultiplexController.switchScene(ViewScenes.LOGIN);
    }
}
