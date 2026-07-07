package multiplex.controllers;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import multiplex.Session;
import multiplex.ViewScenes;
import multiplex.dataclasses.User;

public class CanteenController {
    @FXML
    private Button backButton4;

    @FXML
    private Button confirmItems;

    @FXML
    private Button logoutButton;

    @FXML
    private TilePane snackList;

    @FXML
    private Label totalAmount;

    @FXML
    private Label userName;

    // Continue to Payment page
    @FXML
    public void continueButtonClick() {
        MultiplexController.switchScene(ViewScenes.PAYMENT);
    }
    // Go pack to SelectSeat page
    @FXML
    public void backButtonClick() {
        MultiplexController.switchScene(ViewScenes.SEAT);
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
        // Create list of canteen items
        ArrayList<String> item = new ArrayList<>();
        item.add("claribo");
        item.add("cola");
        item.add("fanta");
        item.add("hotdog");
        item.add("nachos");
        item.add("popcorn");
        item.add("sprite");
        item.add("water");

        displayItems(item);
    }

    public void displayItems(ArrayList<String> items) {
        // Clear grid pane
        snackList.getChildren().clear();
        try {
            for (String item : items) {
                // Create a loader for each day instance
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/multiplex/fxml-files/Snack.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                SnackController snackController = fxmlLoader.getController();
                String path = "/multiplex/ImagesGoHere/"+ item + ".png";
                snackController.setSnackData(path, item);
                snackList.getChildren().add(anchorPane);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
