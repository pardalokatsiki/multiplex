package multiplex.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import multiplex.ViewScenes;

public class SelectDaysController {
    @FXML
    private Button backButton1;

    @FXML
    private Button confirmButton1;

    @FXML
    private VBox daysList;

    @FXML
    private Button logoutButton;

    @FXML
    private Label selectedMovieDesc;

    @FXML
    private ImageView selectedMoviePoster;

    @FXML
    private Label selectedMovieTitle;

    @FXML
    private Label userName;

    // Logout
    @FXML
    public void logoutButtonClick() {
        MultiplexController.switchScene(ViewScenes.LOGIN);
    }
    // Continue to Select Time Page
    @FXML
    public void continueButtonClick() {
        MultiplexController.switchScene(ViewScenes.TIME);
    }
    // Go back to browse page
    @FXML
    public void backButtonClick() {
        MultiplexController.switchScene(ViewScenes.BROWSE);
    }

    public void initialize() {
        BrowseController.selectedMoviePane(userName, selectedMovieTitle, selectedMovieDesc, selectedMoviePoster);
        // Get current date
        Calendar cal = Calendar.getInstance();
        // Set the next 7 days
        ArrayList<String> days = new ArrayList<>();
        // Date show up as Day - Number - Month, ie Sunday 02 August
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE - dd - MMMM");
        for(int i = 0; i < 7; i++)
        {
            days.add(formatter.format(cal.getTime()));
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }

        displayOptions(days);
    }

    public void displayOptions(ArrayList<String> days) {
        // clear vbox
        daysList.getChildren().clear();
        try {
            for (String option : days) {
                // Create a loader for each day instance
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/multiplex/fxml-files/Day.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                DayController dayController = fxmlLoader.getController();
                dayController.setDayButton(option);
                daysList.getChildren().add(anchorPane);
                VBox.setMargin(anchorPane, new Insets(10));
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
