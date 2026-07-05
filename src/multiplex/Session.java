package multiplex;

import multiplex.dataclasses.Movie;
import multiplex.dataclasses.User;

public final class Session {
    private static User currentUser;
    private static Movie selectedMovie;
    private static String seatno;
    private static String date;
    private static String time;
    
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
