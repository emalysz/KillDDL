package Model;


import android.app.Notification;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline implements Serializable {

    private String title, description, color;
    private Date date;
    //private Notification notification;
    private int priority;

    public Deadline(String _title, String _description, Date _date, int _priority, String _color){
        title = _title;
        description = _description;
        date = _date;
        priority = _priority;
        color = _color;
    }

    public String getTitle() {
        return this.title;
    }

    @Override
    public String toString() {
        SimpleDateFormat parseFormat = new SimpleDateFormat("d MMM - K:mm a");
        return this.title + "\t\t -- \t\t" + parseFormat.format(this.date);
    }

    public Date getDate() {
        return this.date;
    }


}
