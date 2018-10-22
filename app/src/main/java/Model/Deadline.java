package Model;


import android.app.Notification;

import java.io.Serializable;
import java.util.Date;

public class Deadline implements Serializable {

    private String title, description, color;
    private Date date;
    //private Notification notification;
    private int priority;
    private Boolean isComplete;

    public Deadline(String _title, String _description, Date _date, int _priority, String _color){
        title = _title;
        description = _description;
        date = _date;
        priority = _priority;
        color = _color;
        isComplete = false;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Boolean isCompleted() {
        return isComplete;
    }

    public void setIsComplete(Boolean isComplete) {
        this.isComplete = isComplete;
    }

    @Override
    public String toString(){
        return this.title + "\t" + this.date.toString();
    }
}
