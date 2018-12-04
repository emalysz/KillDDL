package Controller;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.AdapterView;

import com.example.emmamalysz.killddl.CalendarActivity;
import com.example.emmamalysz.killddl.LoginActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;


import Model.Deadline;
import Model.User;

public class KillDDLController {

    private static KillDDLController killDDLController;
    private User currentUser;
    private DatabaseReference mDatabase;
    private int reload;


    public KillDDLController() {


        this.currentUser = new User("Caroline", "a@usc.edu","pass123",847271702);

    }

    public void logOutUser() {
        currentUser = null;
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
            if (userDeadlines.get(i).getDate().getYear() == date.getYear()) {
                if (userDeadlines.get(i).getDate().getMonth() == date.getMonth()) {
                    monthDeadlines.add(userDeadlines.get(i));
                }
            }
        }
        if (monthDeadlines.size() > 0) {
            if (monthDeadlines.get(0).getMonthPos() != -1) {
                Deadline[] ddls = new Deadline[monthDeadlines.size()];
                for (int i=0; i<monthDeadlines.size(); i++) {
                    ddls[monthDeadlines.get(i).getMonthPos()] = monthDeadlines.get(i);
                }
                List<Deadline> ddlFinal = new ArrayList<Deadline>();
                for (int i=0; i<ddls.length; i++) {
                    ddlFinal.add(ddls[i]);
                }
                return ddlFinal;
            }
        }


        return monthDeadlines;
    }

    public void setDeadlineComplete(Deadline deadline) {
        currentUser.setDeadlineComplete(deadline);
    }
    public User getCurrentUser() {
        if (currentUser == null) {
            this.currentUser = new User("Caroline", "a@usc.edu","pass123",847271702);
        }
        return this.currentUser;
    }

    public void setDatabase () {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public DatabaseReference getDatabase() {
        return mDatabase;
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

                if (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)) {
                    if (cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH)) {
                        dayDeadlines.add(userDeadlines.get(i));
                    }
                }
            }
        }
        if (dayDeadlines.size() > 0) {
            if (dayDeadlines.get(0).getPos() != -1) {
                Deadline[] ddls = new Deadline[dayDeadlines.size()];
                for (int i=0; i<dayDeadlines.size(); i++) {
                    ddls[dayDeadlines.get(i).getPos()] = dayDeadlines.get(i);
                }
                List<Deadline> ddlFinal = new ArrayList<Deadline>();
                for (int i=0; i<ddls.length; i++) {
                    ddlFinal.add(ddls[i]);
                }
                return ddlFinal;
            }
        }

        return dayDeadlines;
    }

     public void setCurrentUser(User aCurrentUser) {
         currentUser = aCurrentUser;
     }

    public void removeDeadline(Deadline deadline) {
        currentUser.removeDeadline(deadline);
        String userID = getCurrentUser().getEmail();
        String[] parts = userID.split("@");
        DatabaseReference mDatabase = getDatabase();
        mDatabase.child("deadlines").removeValue();
        List<Deadline> lDeadlines = currentUser.getDeadlines();
        Map<String, Object> childUpdates = new HashMap<>();
        for (int i=0; i<lDeadlines.size(); i++) {
            String key = mDatabase.child("deadlines").push().getKey();
            childUpdates.put("/deadlines/" + parts[0] + "*" + key, lDeadlines.get(i));

        }
        mDatabase.updateChildren(childUpdates);

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
         String userID = getCurrentUser().getEmail();
         String[] parts = userID.split("@");
         DatabaseReference mDatabase = getDatabase();
         mDatabase.child("deadlines").removeValue();
         List<Deadline> lDeadlines = currentUser.getDeadlines();
         Map<String, Object> childUpdates = new HashMap<>();
         for (int i=0; i<lDeadlines.size(); i++) {
            String key = mDatabase.child("deadlines").push().getKey();
            childUpdates.put("/deadlines/" + parts[0] + "*" + key, lDeadlines.get(i));
         }
        mDatabase.updateChildren(childUpdates);
    }






}
