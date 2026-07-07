package multiplex.controllers;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import multiplex.Session;
import multiplex.ViewScenes;
import multiplex.dataclasses.Movie;
import multiplex.dataclasses.User;

public class SelectTimeController {
    @FXML
    private Button backButton2;

    @FXML
    private Button confirmButton2;

    @FXML
    private Button logoutButton;

    @FXML
    private Label selectedMovieDesc;

    @FXML
    private ImageView selectedMoviePoster;

    @FXML
    private Label selectedMovieTitle;

    @FXML
    private VBox timeList;

    @FXML
    private Label userName;

    @FXML
    public void logoutButtonClick() {
        MultiplexController.switchScene(ViewScenes.LOGIN);
    }

    @FXML
    public void continueButtonClick() {
        MultiplexController.switchScene(ViewScenes.SEAT);
    }

    @FXML
    public void backButtonClick() {
        MultiplexController.switchScene(ViewScenes.DAY);
    }

    public void initialize() {
        BrowseController.selectedMoviePane(userName, selectedMovieTitle, selectedMovieDesc, selectedMoviePoster);

        ArrayList<String> room = new ArrayList<>();
        ArrayList<String> time = new ArrayList<>();
        time.add("19:00");
        time.add("18:00");
        time.add("16:30");

        room.add("Room 01");
        room.add("Room 02");
        room.add("Room 03");

        displayOptions(room, time);
    }

    public void displayOptions(ArrayList<String> room, ArrayList<String> time) {
        // clear vbox
        timeList.getChildren().clear();

        try {
            for (int i = 0; i < room.size(); i++) {
                // Create a loader for each day instance
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/multiplex/fxml-files/Time.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                TimeController timeController = fxmlLoader.getController();
                timeController.setRoomTime(room.get(i), time.get(i));
                timeList.getChildren().add(anchorPane);
                VBox.setMargin(anchorPane, new Insets(10));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
