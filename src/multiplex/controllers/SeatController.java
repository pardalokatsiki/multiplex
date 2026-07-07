package multiplex.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import multiplex.Session;

public class SeatController {

    @FXML
    private AnchorPane dayPane;

    @FXML
    private ToggleButton seatButton;

    @FXML
    private void seatButtonClick() {
        // Set seat number when user clicks on button
        String seat = seatButton.getText();
        Session.setSeatno(seat); 
    }

    public void setSeatButton(String seat) {
        seatButton.setText(seat);
    }
}
