package multiplex.dataclasses;

public class User {
     
    private int id;
    private String username;
    private String passwd;
    
    
    public User(int id,String username,String passwd){
        this.id = id;
        this.username = username;
        this.passwd = passwd;
    }
    
    
    
    // Getters, Setters

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswd() {
        return passwd;
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
}
