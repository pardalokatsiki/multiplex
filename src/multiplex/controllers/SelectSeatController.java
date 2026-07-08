package multiplex.controllers;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import multiplex.ViewScenes;

public class SelectSeatController {
    @FXML
    private Button backButton3;

    @FXML
    private Button confirmButton3;

    @FXML
    private Button logoutButton;

    @FXML
    private TilePane seatList;

    @FXML
    private Label selectedMovieDesc;

    @FXML
    private ImageView selectedMoviePoster;

    @FXML
    private Label selectedMovieTitle;

    @FXML
    private Label userName;

    @FXML
    public void logoutButtonClick() {
        MultiplexController.switchScene(ViewScenes.LOGIN);
    }

    @FXML
    public void continueButtonClick() {
        MultiplexController.switchScene(ViewScenes.PAYMENT);
    }

    @FXML
    public void backButtonClick() {
        MultiplexController.switchScene(ViewScenes.TIME);
    }

    public void initialize() {
        BrowseController.selectedMoviePane(userName, selectedMovieTitle, selectedMovieDesc, selectedMoviePoster);

        int columns = 9;
        int rows = 9;
        ArrayList<String> seats = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            // Seat option A - I
            char temp = (char) +('A' + i);
            // Seat number 1 - 9
            for (int j = 0; j < columns; j++) {
                seats.add(temp + "" + (j + 1));
            }
        }
        displayOptions(seats);
    }

    public void displayOptions(ArrayList<String> seats) {
        // clear vbox
        seatList.getChildren().clear();
        try {
            for (String seat : seats) {
                // Create a loader for each day instance
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/multiplex/fxml-files/Seat.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                SeatController seatController = fxmlLoader.getController();
                seatController.setSeatButton(seat);
                seatList.getChildren().add(anchorPane);
                VBox.setMargin(anchorPane, new Insets(10));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
