package multiplex.dataclasses;

public class Movie {
 
    private int id;
    private String title;
    private int duration_min;
    private String info;
    
    
    public Movie(int id, String title, int duration_min, String info){
        
        this.id = id;
        this.title = title;
        this.duration_min = duration_min;
        this.info = info;
        
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration_min() {
        return duration_min;
    }

    public String getInfo() {
        return info;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDuration_min(int duration_min) {
        this.duration_min = duration_min;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    
}