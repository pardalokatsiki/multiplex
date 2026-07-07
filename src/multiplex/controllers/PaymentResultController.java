package multiplex.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import multiplex.Session;
import multiplex.ViewScenes;
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

    // Browse for a new movie
    @FXML
    public void browseAgainButton() {
        MultiplexController.switchScene(ViewScenes.BROWSE);
    }
    // Logout
    @FXML
    public void logoutButtonClick() {
        MultiplexController.switchScene(ViewScenes.LOGIN);
    }
    
    public void initialize() {
        // Set current user credentials
        User user = Session.getCurrentUser();
        userName.setText(user.getUsername());

        if(Session.isBookTicket()) {
            paymentMessage.setText("Booking Successful. We Hope You Enjoy Your Cinematic Experience!");
        } else {
            paymentMessage.setText("Booking Not Successful. Please Revisit Your Input Details");
        }
    }
}
