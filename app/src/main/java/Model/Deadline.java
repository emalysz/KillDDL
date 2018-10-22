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
    private int notification;
    private int color;

    @Override
    public int compareTo(Deadline other){
        return date.compareTo(other.getDate());
    }
    public Deadline(String _title, String _description, Date _date, int _priority, int _notification, int _color){
        title = _title;
        description = _description;
        date = _date;
        priority = _priority;
        notification = _notification;
        color = _color;
    }
    public String getDescription(){
        return description;
    }
    public String getTitle(){
        return title;
    }


    @Override
    public String toString() {
        SimpleDateFormat parseFormat = new SimpleDateFormat("d MMM K:mm a");
        return this.title + "- \t\t" + parseFormat.format(this.date);
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


}
