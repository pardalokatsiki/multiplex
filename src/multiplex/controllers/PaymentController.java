package multiplex.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import multiplex.Session;
import multiplex.ViewScenes;
import multiplex.dataclasses.Movie;
import multiplex.dataclasses.User;
import multiplex.serviceclasses.PaymentService;
import multiplex.serviceclasses.TicketService;

public class PaymentController {
    @FXML
    private Button backButton5;

    @FXML
    private TextField cardNumber;

    @FXML
    private Button confirmButton5;

    @FXML
    private PasswordField cvvField;

    @FXML
    private TextField emailConfirmField;

    @FXML
    private DatePicker expiryDate;

    @FXML
    private Button logoutButton;

    @FXML
    private Text movieDate;

    @FXML
    private Text moviePrice;

    @FXML
    private Text movieSeat;

    @FXML
    private Text movieTime;

    @FXML
    private Text movieTitle;

    @FXML
    private TextField ownerOfCardField;

    @FXML
    private Text paymentErrorMsg;

    @FXML
    private Label selectedMovieDesc;

    @FXML
    private ImageView selectedMoviePoster;

    @FXML
    private Label selectedMovieTitle;

    @FXML
    private Label userName;

    // Continue to Payment Result page
    @FXML
    public void continueButtonClick() {
        // Call ticket service to use database
        TicketService service = new TicketService();
        Movie movie = Session.getSelectedMovie();
        User user = Session.getCurrentUser();
        // Book ticket in database
        boolean booking = service.bookTicket(movieSeat.getText(), 9.50, movie.getId(), user.getId());
        // Validate payment values
        boolean validation = validatePurchase();
        if(validation) {
            Session.setBookTicket(booking);
            MultiplexController.switchScene(ViewScenes.RESULT);
        } else if(!validation) {
            paymentErrorMsg.setText("Payment Failed. Check Inputs");
        }
    }
    // Go back to Canteen Page
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
        BrowseController.selectedMoviePane(userName, selectedMovieTitle, selectedMovieDesc, selectedMoviePoster);
        movieTitle.setText(selectedMovieTitle.getText());
        movieDate.setText(Session.getDate());
        movieTime.setText(Session.getTime());
        movieSeat.setText(Session.getSeatno());
    }

    public Boolean validatePurchase() {
        PaymentService service = new PaymentService();
        String cardno = cardNumber.getText();
        String holderName = ownerOfCardField.getText();
        LocalDate date = expiryDate.getValue();
        String cardDate = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String cvv = cvvField.getText();
        boolean state = service.processPayment(cardno, holderName, cardDate, cvv);
        return state;
    }
}
