package Model;


import android.app.Notification;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class Deadline implements Serializable, Comparable<Deadline> {

    private String title, description;
    private Date date;
    private int priority;
    private Boolean isComplete;
    private int notification;
    private int color;
    private int frequency;

    @Override
    public int compareTo(Deadline other){
        return date.compareTo(other.getDate());
    }
    public Deadline(String _title, String _description, Date _date, int _priority, int _notification, int _color, int _frequency){
        title = _title;
        description = _description;
        date = _date;
        priority = _priority;
        notification = _notification;
        color = _color;
        isComplete = false;

        frequency = _frequency;
    }

    public String getTitle(){
        return title;
    }


    @Override
    public String toString() {
        SimpleDateFormat parseFormat = new SimpleDateFormat("d MMM K:mm a");
        return this.title + "- \t\t" + parseFormat.format(this.date);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return this.date;
    }




    public int getPriority() {
        return priority;
    }

    public int getNotification() {
        return notification;
    }

    public int getColor() {
        return color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setDate(Date date) {
        this.date = date;
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


    public int getFrequency(){
        return frequency;
    }

}
