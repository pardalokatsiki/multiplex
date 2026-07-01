package multiplex;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class MultiplexController {
    @FXML
    public void loginButtonClick(ActionEvent login) {
        String text = userField.getText();
        String pwd = passField.getText();
        if(!text.isEmpty() && !pwd.isEmpty())
            System.out.println("Looking for user: " + text + " with password: " + pwd);
    }
    
    private Stage stage;
    private Scene scene;
    private Parent root;
   
    @FXML
    public void registerLinkClick(ActionEvent event) throws IOException {
        //Move to Register Page Scene
        root = FXMLLoader.load(getClass().getResource("CreateUserPageScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void createAccountClick(ActionEvent event) throws IOException {
        String username = createUsername.getText();
        String password = createPass.getText();
        String email = createEmail.getText();
        
        //TODO Add SQL query to save user credentials
        
        //Move to Login Page Scene
        root = FXMLLoader.load(getClass().getResource("LoginPageScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

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
    private Button createAccountButton;
    
    @FXML
    private TextField createUsername;
    
    @FXML
    private TextField createEmail;
    
    @FXML
    private TextField createPass;
}
