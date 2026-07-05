package multiplex;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;

public class SeatController {

    @FXML
    private AnchorPane dayPane;

    @FXML
    private ToggleButton seatButton;

    @FXML
    private void seatButtonClick() {
        String seat = seatButton.getText();
        Session.setSeatno(seat); 
    }

    public void setSeatButton(String seat) {
        seatButton.setText(seat);
    }
}
