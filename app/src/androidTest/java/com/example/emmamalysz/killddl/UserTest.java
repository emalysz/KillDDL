package com.example.emmamalysz.killddl;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import Model.Deadline;
import Model.User;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class UserTest {

    @Test
    public void userConstructorTest(){
        User user = new User("jen", "jen@gmail.com", "test123", 2746467);
        assertTrue(!user.equals(null));
    }

    @Test
    public void addDeadlineTest(){
        User user0 = new User("freq0", "jen@gmail.com", "test123", 2746467);
        Deadline d = new Deadline("Finish Project 2.4", "Test cases", Calendar.getInstance().getTime(),
                2, 1, 2, 0);
        user0.addDeadline(d);
        assertTrue(!user0.getDeadlines().equals(null));



        User user1 = new User("freqDaily", "jen@gmail.com", "test123", 2746467);
        Deadline ddlD = new Deadline("BUAD 311 HW", "Finish pre-class assignment", new Date(11-04-2108),
                2, 1, 0, 1);
        user1.addDeadline(ddlD);
        assertEquals(366, user1.getDeadlines().size());


        User user2 = new User("freqMonthly", "jen@gmail.com", "test123", 2746467);
        Deadline ddlM = new Deadline("PYSC 165 Quiz", "Finish pre-class assignment", new Date(11-04-2108),
                2, 1, 0, 2);
        user2.addDeadline(ddlM);
        assertEquals(13, user2.getDeadlines().size());
    }

    @Test
    public void removeDeadlineTest(){
        User user = new User("freq0", "jen@gmail.com", "test123", 2746467);
        Deadline d = new Deadline("Finish Project 2.4", "Test cases", Calendar.getInstance().getTime(),
                2, 1, 2, 0);
        user.addDeadline(d);
        user.removeDeadline(d);

        assertTrue(user.getDeadlines().isEmpty());

    }

    @Test
    public void setDeadlineCompleteTest(){
        User user = new User("freq0", "jen@gmail.com", "test123", 2746467);
        Deadline d = new Deadline("Finish Project 2.4", "Test cases", Calendar.getInstance().getTime(),
                2, 1, 2, 0);
        user.addDeadline(d);
        user.setDeadlineComplete(d);
        assertTrue(user.getDeadlines().get(0).isCompleted());

    }
}
