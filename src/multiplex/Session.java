package multiplex;

import multiplex.dataclasses.Movie;
import multiplex.dataclasses.User;

// Class to handle data for current user session
// Values get passed from controller to controller
public final class Session {
    private static User currentUser;    // Current logged in User
    private static Movie selectedMovie; // Current selected movie
    private static String seatno;       // Chosen Seat
    private static String date;         // Chosen movie date
    private static String time;         // Chosen movie time
    private static boolean bookTicket;  // booking result
    // Getters and Setters
    public static boolean isBookTicket() {
        return bookTicket;
    }

    public static void setBookTicket(boolean bookTicket) {
        Session.bookTicket = bookTicket;
    }

    public static String getTime() {
        return time;
    }

    public static void setTime(String time) {
        Session.time = time;
    }


    public static String getDate() {
        return date;
    }

    public static void setDate(String date) {
        Session.date = date;
    }
    
    public static String getSeatno() {
        return seatno;
    }

    public static void setSeatno(String seatno) {
        Session.seatno = seatno;
    }

    public static Movie getSelectedMovie() {
        return selectedMovie;
    }

    public static void setSelectedMovie(Movie selectedMovie) {
        Session.selectedMovie = selectedMovie;
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }
}
