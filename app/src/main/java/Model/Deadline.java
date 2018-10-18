package Model;


import android.app.Notification;

import java.io.Serializable;
import java.util.Date;

public class Deadline implements Serializable {

    private String title, description;
    private Date date;
    private int priority;
    private int notification;
    private int color;

    public Deadline(String _title, String _description, Date _date, int _priority, int _notification, int _color){
        title = _title;
        description = _description;
        date = _date;
        priority = _priority;
        notification = _notification;
        color = _color;
    }


}
