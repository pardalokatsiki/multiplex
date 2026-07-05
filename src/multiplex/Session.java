package multiplex;

import multiplex.dataclasses.Movie;
import multiplex.dataclasses.User;

public final class Session {
    private static User currentUser;
    private static Movie selectedMovie;
    
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
