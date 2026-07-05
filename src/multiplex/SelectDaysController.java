package multiplex;

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
import multiplex.dataclasses.Movie;
import multiplex.dataclasses.User;

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

    @FXML
    public void logoutButtonClick() {
        MultiplexController.switchScene(ViewScenes.LOGIN);
    }

    @FXML
    public void continueButtonClick() {
        MultiplexController.switchScene(ViewScenes.TIME);
    }

    @FXML
    public void backButtonClick() {
        MultiplexController.switchScene(ViewScenes.BROWSE);
    }

    public void initialize() {
        // Set current user credentials
        User user = Session.getCurrentUser();
        userName.setText(user.getUsername());

        // Set the previously selected movie
        Movie movie = Session.getSelectedMovie();
        selectedMovieDesc.setText(movie.getInfo());
        selectedMovieTitle.setText(movie.getTitle());
        
        // TODO: change it so that the week is based on user's date instance
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_WEEK);

        // Set days
        ArrayList<String> days = new ArrayList<>();
        days.add("Monday");
        days.add("Tuesday");
        days.add("Wednesday");
        days.add("Thursday");
        days.add("Friday");
        days.add("Saturday");
        days.add("Sunday");

        displayOptions(days);
    }

    public void displayOptions(ArrayList<String> days) {
        // clear vbox
        daysList.getChildren().clear();
        
        try {
            for (String option : days) {
                // Create a loader for each movie poster
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("fxml-files/Day.fxml"));
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
