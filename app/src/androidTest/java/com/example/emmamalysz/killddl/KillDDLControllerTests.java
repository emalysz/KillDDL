package com.example.emmamalysz.killddl;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.Test;

import java.util.Calendar;

import Controller.KillDDLController;
import Model.Deadline;
import Model.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
        assertNull(test.getCurrentUser());
        test.setCurrentUser(u);
        assertNotNull(test.getCurrentUser());
    }

    @Test
    public void testGetDeadlines() {
        User u = new User("Caroline", "a@usc.edu","pass123",847271702);
        //u.addDeadline(new Deadline("Exam","Itp exam",Calendar.getInstance().getTime(),));
    }


}
