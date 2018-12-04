package Model;
import android.content.Intent;
import android.util.Log;

import com.example.emmamalysz.killddl.CalendarActivity;
import com.example.emmamalysz.killddl.LoginActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class User {
    private String name;
    private String email;
    private String password;
    private long phoneNumber;
    List<Deadline> deadlines;

    public User(String _name, String _email, String _password, long _phoneNumber){
        name = _name;
        password = _password;
        email = _email;
        phoneNumber = _phoneNumber;
        deadlines = new ArrayList<Deadline>();
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addDeadline(Deadline d){
        deadlines.add(d);
        if (d.getFrequency() == 1) {
            Calendar c = Calendar.getInstance();
            c.setTime(d.getDate());
            long time = c.getTimeInMillis();
            for (int i=0; i<365; i++) {
                time = time + 86400000L;
                deadlines.add(new Deadline(d.getTitle(), d.getDescription(), new Date(time),
                        d.getPriority(), d.getNotification(), d.getColor(), d.getFrequency()));
            }
        } else if (d.getFrequency() == 2){
            Calendar c = Calendar.getInstance();
            c.setTime(d.getDate());
            long time = c.getTimeInMillis();
            for (int i = 0; i < 52; i++){
                time = time + 604800000L;
                deadlines.add(new Deadline(d.getTitle(), d.getDescription(), new Date(time),
                        d.getPriority(), d.getNotification(), d.getColor(), d.getFrequency()));
            }
        }
        else if (d.getFrequency() == 3) {
            Calendar c = Calendar.getInstance();
            c.setTime(d.getDate());
            long time = c.getTimeInMillis();
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(d.getDate());
            int month = cal2.get(Calendar.MONTH);
            for (int i=0; i<12; i++) {
                if (month == 8 || month ==  3 || month ==  5 || month ==  10){
                    time = time + 2593000000L ;
                }
                else if (month == 12 || month == 2 || month == 4 || month == 6 || month == 7 ||
                        month == 9 || month == 11){
                    time = time + 2679000000L;
                }
                else if (month == 1){
                    time = time + 2420000000L;

                }

                deadlines.add(new Deadline(d.getTitle(), d.getDescription(), new Date(time),
                        d.getPriority(), d.getNotification(), d.getColor(), d.getFrequency()));
                if (month == 12){
                    month  = 0;
                }
                month++;
            }

        }
        Collections.sort(deadlines);
    }

    public void removeDeadline(Deadline d){

        deadlines.remove(d);
//
    }


    public List<Deadline> getDeadlines() {
        return this.deadlines;
    }

    public void setDeadlineComplete(Deadline deadline) {
        for (int i=0; i<deadlines.size(); i++) {
            if (deadlines.get(i).equals(deadline)) {
                deadlines.get(i).setIsComplete();
            }
        }
    }

    public void setDeadlines(List<Deadline> deadlines) { this.deadlines = deadlines; }
}
