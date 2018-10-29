package com.example.emmamalysz.killddl;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.Test;

import java.util.Calendar;
import java.util.List;

import Controller.KillDDLController;
import Model.Deadline;
import Model.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class KillDDLControllerTests {

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.emmamalysz.killddl", appContext.getPackageName());
    }

    @Test
    public void getControllerInstanceTest() {
        assertNotNull(KillDDLController.getInstance());
    }

    @Test
    public void setCurrentUserTest() {
        User u = new User("Caroline", "a@usc.edu","pass123",847271702);
        KillDDLController test = KillDDLController.getInstance();
        test.setCurrentUser(u);
        assertNotNull(test.getCurrentUser());
        assertEquals(u, test.getCurrentUser());
    }

    @Test
    public void testGetDeadlines() {
        User u = new User("Caroline", "a@usc.edu","pass123",847271702);
        u.addDeadline(new Deadline("Exam","Itp exam",Calendar.getInstance().getTime(),
                1, 1, 1, 0));
        KillDDLController test = KillDDLController.getInstance();
        test.setCurrentUser(u);
        List<Deadline> deadlines = test.getDeadlines();
        assertEquals(deadlines.size(), 1);
    }

    @Test
    public void testGetMonthlyDeadlines() {
        User u = new User("Caroline", "a@usc.edu","pass123",847271702);
        u.addDeadline(new Deadline("Exam","Itp exam",Calendar.getInstance().getTime(),
                1, 1, 1, 0));
        KillDDLController test = KillDDLController.getInstance();
        test.setCurrentUser(u);
        List<Deadline> deadlines = test.getMonthlyDeadlines(Calendar.getInstance().getTime());
        assertEquals(deadlines.size(), 1);
    }

    @Test
    public void testSetDeadlineComplete() {
        User u = new User("Caroline", "a@usc.edu","pass123",847271702);
        Deadline d = new Deadline("Exam","Itp exam",Calendar.getInstance().getTime(),
                1, 1, 1, 0);
        u.addDeadline(d);
        KillDDLController test = KillDDLController.getInstance();
        test.setCurrentUser(u);
        test.setDeadlineComplete(d);
        assertEquals(true, d.isCompleted());
    }

    @Test
    public void testGetDayDeadlines() {
        User u = new User("Caroline", "a@usc.edu","pass123",847271702);
        u.addDeadline(new Deadline("Exam","Itp exam",Calendar.getInstance().getTime(),
                1, 1, 1, 0));
        u.addDeadline(new Deadline("Another Exam","Itp exam",Calendar.getInstance().getTime(),
                1, 1, 1, 0));
        KillDDLController test = KillDDLController.getInstance();
        test.setCurrentUser(u);
        List<Deadline> deadlines = test.getDayDeadlines(Calendar.getInstance().getTime());
        assertEquals(deadlines.size(), 2);
    }

    @Test
    public void testAddDeadline() {
        User u = new User("Caroline", "a@usc.edu","pass123",847271702);
        KillDDLController test = KillDDLController.getInstance();
        test.setCurrentUser(u);
        test.addDeadline(new Deadline("Another Exam","Itp exam",Calendar.getInstance().getTime(),
                1, 1, 1, 0));
        List<Deadline> deadlines = test.getDeadlines();
        assertEquals(deadlines.size(), 1);
    }

    @Test
    public void testRemoveDeadline() {
        User u = new User("Caroline", "a@usc.edu","pass123",847271702);
        KillDDLController test = KillDDLController.getInstance();
        test.setCurrentUser(u);
        test.addDeadline(new Deadline("Another Exam","Itp exam",Calendar.getInstance().getTime(),
                1, 1, 1, 0));
        Deadline toRemove = new Deadline("Another Exam 2","Itp exam",Calendar.getInstance().getTime(),
                1, 1, 1, 0);
        test.addDeadline(toRemove);
        test.removeDeadline(toRemove);
        List<Deadline> deadlines = test.getDeadlines();
        assertEquals(deadlines.size(), 1);

    }

    @Test
    public void testEditDeadline() {
        User u = new User("Caroline", "a@usc.edu","pass123",847271702);
        KillDDLController test = KillDDLController.getInstance();
        test.setCurrentUser(u);
        Deadline d = new Deadline("Another Exam","Itp exam",Calendar.getInstance().getTime(),
                1, 1, 1, 0);
        test.addDeadline(d);
        test.editDeadline(0, new Deadline("Another Exam Edited","Itp exam",Calendar.getInstance().getTime(),
                1, 1, 1, 0));
        assertTrue(test.getDeadlines().get(0).getTitle().equals("Another Exam Edited"));
        assertFalse(test.getDeadlines().get(0).getTitle().equals("Another Exam"));

    }

    @Test
    public void testGetDeadlineId() {

        User u = new User("Caroline", "a@usc.edu","pass123",847271702);
        KillDDLController test = KillDDLController.getInstance();
        test.setCurrentUser(u);
        Deadline d = new Deadline("Another Exam","Itp exam",Calendar.getInstance().getTime(),
                1, 1, 1, 0);
        test.addDeadline(d);
        assertEquals(test.getDeadlineID(d), 0);

    }




}
