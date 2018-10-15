package Model;


import android.app.Notification;

import java.util.Date;

public class Deadline {

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


}
