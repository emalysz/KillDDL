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

    public KillDDLController() {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        Calendar cal3 = Calendar.getInstance();
        cal1.set(2018, 9, 10);
        cal2.set(2018, 9, 15);
        cal3.set(2018,8,20);
        this.currentUser = new User("Caroline", "a@usc.edu", "hi",
                84727172);
        this.currentUser.addDeadline(new Deadline("ITP Exam", "study for exam", cal1.getTime(), 1, 1, 0, 1));
        this.currentUser.addDeadline(new Deadline("Electricity Bill", "pay online", cal2.getTime(), 2, 1, 1, 1));
        this.currentUser.addDeadline( new Deadline("103 PA", "code in c++", cal3.getTime(), 1, 1, 2, 1));

    }

    public static KillDDLController getInstance() {
        if (killDDLController == null) {
            killDDLController = new KillDDLController();
        }
        return killDDLController;
    }


    /**
     * Tentative member variables we will have, add more if needed
     */

      private User currentUser;
      //private MainView mainView;
      private CalendarActivity monthlyView;
//    private DailyView dailyView;
//    private DeadlineView deadlineView;
//    private EditView editView;




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


    /**
     * Tentative classes for the controller, add more if needed
     *
     */

     public void setCurrentUser(User aCurrentUser) {
         currentUser = aCurrentUser;
     }



    public void removeDeadline(Deadline deadline) {
//        List<Deadline> temp = currentUser.getDeadlines();
//        temp.remove(deadline);
//        currentUser.editDeadlines(temp);
        currentUser.removeDeadline(deadline);
    }
    public void addDeadline(Deadline deadline) {
         currentUser.addDeadline(deadline);
    }
//
//    public void addDeadline(Deadline deadline) {
//
//    }
//
//    public void validateLogin(String username, String password) {
//
//    }
//
//    public void openMainView() {
//
//    }
//
//    public void openDeadlineView() {
//
//    }
//
//    public void openEditView() {
//
//    }
//
//    public void openDailyView() {
//
//    }



}
