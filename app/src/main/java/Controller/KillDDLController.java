package Controller;

import java.util.ArrayList;
import java.util.Date;

import Model.Deadline;
import Model.User;

public class KillDDLController {

    /**
     * Tentative member variables we will have, add more if needed
     */

//    private User currentUser;
//    private MainView mainView;
//    private MonthlyView monthlyView;
//    private DailyView dailyView;
//    private DeadlineView deadlineView;
//    private EditView editView;
    private User mCurrentUser;

    private static KillDDLController killDDLController;

    private KillDDLController() {

//        User user = new User("test", "test@gmail.com", "tester123", 123);
//        setCurrentUser(user);
//        mCurrentUser.addDeadline(new Deadline("test", "tester", new Date("2018-10-18"),
//                0,0,0,0));
    }

    public static KillDDLController getInstance() {
        if (killDDLController == null) {
            killDDLController = new KillDDLController();
        }
        return killDDLController;
    }

    /**
     * Tentative classes for the controller, add more if needed
     *
     */

     public void setCurrentUser(User aCurrentUser) {
         mCurrentUser = aCurrentUser;
     }

     public ArrayList<Deadline> getDeadlines() {
         return mCurrentUser.getDeadlines();
     }

    public void removeDeadline(Deadline deadline) {
        ArrayList<Deadline> temp = mCurrentUser.getDeadlines();
        temp.remove(deadline);
        mCurrentUser.editDeadlines(temp);
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
