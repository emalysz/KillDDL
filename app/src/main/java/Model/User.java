package Model;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String email;
    private String password;
    private int phoneNumber;
    List<Deadline> deadlines;

    public User(String _name, String _email, String _password, int _phoneNumber){
        name = _name;
        password = _password;
        email = _email;
        phoneNumber = _phoneNumber;
        deadlines = new ArrayList<Deadline>();
    }

    public Boolean authenticateUser(String email, String password){
        Boolean isUser = false;

        return isUser;
    }

    public void addDeadline(Deadline d){
        deadlines.add(d);
    }

    public void removeDeadline(Deadline d){

        deadlines.remove(d);
    }

    public void editDeadlines(List<Deadline> _deadlines){
        deadlines = _deadlines;
    }

    public Boolean deadlineExists(Deadline d){
        Boolean isDeadline = false;

        return isDeadline;
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
}
