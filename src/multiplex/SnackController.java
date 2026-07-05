package multiplex;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SnackController {
    @FXML
    private ImageView snackIcon;

    @FXML
    private Label snackName;

    @FXML
    private Spinner<?> snackSpinner;

    public void setSnackData(String snackPath, String snack) {
        snackName.setText(snack);
        var image = new Image(getClass().getResourceAsStream(snackPath));
        snackIcon.setImage(image);
    }
}
