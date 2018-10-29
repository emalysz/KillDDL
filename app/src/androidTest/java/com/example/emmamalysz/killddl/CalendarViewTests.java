package com.example.emmamalysz.killddl;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class CalendarViewTests {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.emmamalysz.killddl", appContext.getPackageName());
    }

    @Test
    public void currentDateHighlightedTest() {

    }

    // test that the current date is highlighted

    // test that a color shows up for a deadline with an event that day
    // (check to see if adding event to calendar with correct colors)

    // check that deadlines are displayed in list view

    // when a day is clicked, the deadlines for that day are displayed

    // when it is swiped to a new month, that month's deadlines are shown,

    // check that deadlines is deleted with delete button and does not show up

   /*
    UI Tests (with espresso)
    */

    // check that when daily button clicked goes to daily view page

    // check that when deadline clicked, redirected to deadline page with correct deadline

    // check that delete button deletes deadline
}
