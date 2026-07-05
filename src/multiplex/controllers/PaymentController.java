package multiplex.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import multiplex.Session;
import multiplex.ViewScenes;
import multiplex.dataclasses.Movie;
import multiplex.dataclasses.User;
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

    @FXML
    public void continueButtonClick() {
        //MultiplexController.switchScene(ViewScenes.RESULT);
        TicketService service = new TicketService();
        
        Movie movie = Session.getSelectedMovie();
        User user = Session.getCurrentUser();
        
        boolean booking = service.bookTicket(movieSeat.getText(), 9.50, movie.getId(), user.getId());
        
        Session.setBookTicket(booking);
        MultiplexController.switchScene(ViewScenes.RESULT);
    }

    @FXML
    public void backButtonClick() {
        MultiplexController.switchScene(ViewScenes.CANTEEN);
    }

    @FXML
    public void logoutButtonClick() {
        MultiplexController.switchScene(ViewScenes.LOGIN);
    }

    public void initialize() {
        selectedMoviePane();
    }

    public void selectedMoviePane() {
        // Set current user credentials
        User user = Session.getCurrentUser();
        userName.setText(user.getUsername());

        // Set the previously selected movie
        Movie movie = Session.getSelectedMovie();
        selectedMovieDesc.setText(movie.getInfo());
        selectedMovieTitle.setText(movie.getTitle());
        movieTitle.setText(movie.getTitle());
        movieDate.setText(Session.getDate());
        movieTime.setText(Session.getTime());
        System.out.println(Session.getSeatno());
        movieSeat.setText(Session.getSeatno());
        var image = new Image(getClass().getResourceAsStream(movie.getImage()));
        selectedMoviePoster.setImage(image);
    }
}
