package multiplex.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import multiplex.Session;

public class TimeController {
    @FXML
    private AnchorPane dayPane;

    @FXML
    private ToggleButton timeButton;

    @FXML
    private Label roomLabel;
    
    @FXML
    public void timeButtonClick() {
        String time = timeButton.getText();
        Session.setTime(time);
    }

    public void setRoomTime(String room, String time) {
        roomLabel.setText(room);
        timeButton.setText(time);
    }
}
