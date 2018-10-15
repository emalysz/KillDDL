package Model;
import java.util.ArrayList;
public class User {
    private String name;
    private String email;
    private String password;
    private int phoneNumber;
    ArrayList<Deadline> deadlines;

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
        int index = deadlines.indexOf(d);

        deadlines.remove(index);
    }

    public void editDeadline(Deadline d){

    }

    public Boolean deadlineExists(Deadline d){
        Boolean isDeadline = false;

        return isDeadline;
    }
}
