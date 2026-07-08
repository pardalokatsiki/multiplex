package multiplex.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import multiplex.Session;

public class DayController {
    @FXML
    private ToggleButton dayButton;

    @FXML
    private AnchorPane dayPane;

    // Set date for the current user session
    @FXML
    public void dateClick() {
        String date = dayButton.getText();
        Session.setDate(date);
    }

    public void setDayButton(String day)
    {
        dayButton.setText(day);
    }
}
