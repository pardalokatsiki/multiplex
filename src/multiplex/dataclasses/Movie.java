package multiplex.dataclasses;

public class Movie {
 
    private int id;
    private String title;
    private int duration_min;
    private String info;
    private String showDate;
    private String showTime;
    
    
    public Movie(int id, String title, int duration_min, String info,String showDate,String showTime){
        
        this.id = id;
        this.title = title;
        this.duration_min = duration_min;
        this.info = info;
        this.showDate = showDate;
        this.showTime = showTime;
        
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

     public String getShowDate() {
        return showDate;
    }

    public String getShowTime() {
        return showTime;
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

    public void setShowDate(String showDate) {
        this.showDate = showDate;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }
    
     
    
}