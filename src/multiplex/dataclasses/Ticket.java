package multiplex.dataclasses;

public class Ticket {
    
    private int id;         //Unique identifier for the ticket
    private double price;   //Price of the ticket
    private String seatNo;  //Seat number for the ticket
    private int movieId;    //ID of the movie for which the ticket is purchased
    private int userId;     //ID of the user who purchased the ticket

    //Constructor for the Ticket class
    public Ticket(int id, double price, String seatNo, int movieId, int userId) {
        this.id = id;
        this.price = price;
        this.seatNo = seatNo;
        this.movieId = movieId;
        this.userId = userId;
    }

    //Getters and Setters
    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public int getMovieId() {
        return movieId;
    }

    public int getUserId() {
        return userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
}

    
    

