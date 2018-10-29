package com.example.emmamalysz.killddl;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import Controller.KillDDLController;
import Model.Deadline;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class DeadlineTests {

    @Test
    public void constructorDeadlineTest() {
        Deadline d1 = new Deadline("Test", "CSCI 310 Test",
                Calendar.getInstance().getTime(),1, 0, 1, 0);
        assertTrue(!d1.equals(null));
    }


    @Test
    public void equalsMethodTest() {
        Deadline d1 = new Deadline("Test", "CSCI 310 Test",
                Calendar.getInstance().getTime(),1, 0, 1, 0);
        Deadline d2 = d1;
        assertEquals(d1, d2);
    }



}
