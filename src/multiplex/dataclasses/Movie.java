package multiplex.dataclasses;

public class Movie {
 
    private int id; //Unique identifier for the movie
    private String title; //Title of the movie
    private int duration_min; //Duration of the movie in minutes
    private String info; //Information about the movie
    private String showDay; //Day for when the movie is shown
    private String showTime; //Time for when the movie is shown
    private String imgPath;
    

    //Constructor for the Movie class
    public Movie(int id, String title, int duration_min, String info,String showDay,String showTime){
        
        this.id = id;
        this.title = title;
        this.duration_min = duration_min;
        this.info = info;
        this.showDay = showDay;
        this.showTime = showTime;
        
    }

    //Getters and Setters
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

     public String getShowDay() {
        return showDay;
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

    public void setShowDay(String showDay) {
        this.showDay = showDay;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }
    
}