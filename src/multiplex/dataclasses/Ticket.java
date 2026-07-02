
package multiplex.dataclasses;

public class Ticket {
    
    private int id;
    private double price;
    private String seatNo;
    // two fk movies_id,users_id

    public Ticket(int id, double price, String seatNo) {
        this.id = id;
        this.price = price;
        this.seatNo = seatNo;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getSeatNo() {
        return seatNo;
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
    
    
}

    
    

