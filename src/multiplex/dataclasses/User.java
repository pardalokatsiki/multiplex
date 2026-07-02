package multiplex.dataclasses;

public class User {
     
    private int id; //Unique identifier for the user
    private String username; //Username of the user
    private String passwd; //Password of the user
    private String email; //Email of the user
    
    //Constructor for the User class
    public User(int id,String username,String passwd,String email){
        this.id = id;
        this.username = username;
        this.passwd = passwd;
        this.email = email;
    }
    
    
    
    //Getters and Setters

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswd() {
        return passwd;
    }

    public String getEmail() {
        return email;
    }
    

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
