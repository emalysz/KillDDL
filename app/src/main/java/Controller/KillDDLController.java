package Controller;


import android.content.Context;
import android.content.Intent;
import android.widget.AdapterView;

import com.example.emmamalysz.killddl.CalendarActivity;
import com.example.emmamalysz.killddl.LoginActivity;

import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import java.util.ArrayList;
import java.util.Date;


import Model.Deadline;
import Model.User;

public class KillDDLController {

    private static KillDDLController killDDLController;
    private User currentUser;

    public KillDDLController() {

        this.currentUser = null;

    }

    public static KillDDLController getInstance() {
        if (killDDLController == null) {
            killDDLController = new KillDDLController();
        }
        return killDDLController;
    }


    public List<Deadline> getDeadlines() {
        Collections.sort(currentUser.getDeadlines());
        return currentUser.getDeadlines();
    }

    public List<Deadline> getMonthlyDeadlines(Date date) {
        List<Deadline> userDeadlines = currentUser.getDeadlines();
        List<Deadline> monthDeadlines = new ArrayList<Deadline>();
        for (int i=0; i<userDeadlines.size(); i++) {
            if (userDeadlines.get(i).getDate().getMonth() == date.getMonth()) {
                monthDeadlines.add(userDeadlines.get(i));
            }
        }
        Collections.sort(monthDeadlines);
        return monthDeadlines;
    }

    public void setDeadlineComplete(Deadline deadline) {
        currentUser.setDeadlineComplete(deadline);
    }
    public User getCurrentUser() {
        return this.currentUser;
    }

    public List<Deadline> getDayDeadlines(Date date) {

        List<Deadline> userDeadlines = currentUser.getDeadlines();
        List<Deadline> dayDeadlines = new ArrayList<Deadline>();
        for (int i=0; i<userDeadlines.size(); i++) {
            if (userDeadlines.get(i).getDate().getMonth() == date.getMonth()) {
                Calendar cal1 = Calendar.getInstance();
                Calendar cal2 = Calendar.getInstance();
                cal1.setTime(userDeadlines.get(i).getDate());
                cal2.setTime(date);
                if (cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH)) {
                    dayDeadlines.add(userDeadlines.get(i));
                }
            }
        }
        Collections.sort(dayDeadlines);
        return dayDeadlines;
    }

     public void setCurrentUser(User aCurrentUser) {
         currentUser = aCurrentUser;
     }



    public void removeDeadline(Deadline deadline) {
        currentUser.removeDeadline(deadline);
    }
    public void addDeadline(Deadline deadline) {
         currentUser.addDeadline(deadline);
    }

    public int getDeadlineID(Deadline myDDL) {
         for (int i = 0; i < currentUser.getDeadlines().size(); i++) {
             if (myDDL.getId() == currentUser.getDeadlines().get(i).getId()) {
                 return i;
             }
         }

         return 0;
    }

    public void editDeadline(int _id, Deadline editedDeadline) {
         List<Deadline> listDeadlines = getDeadlines();
         listDeadlines.set(_id, editedDeadline);
    }




}
