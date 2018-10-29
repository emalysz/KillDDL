package com.example.emmamalysz.killddl;

import android.app.Activity;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import java.util.Calendar;

//import androidx.test.espresso.Espresso;

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

    @Rule
    public ActivityTestRule<CalendarActivity> mActivityTestRule = new ActivityTestRule<>(CalendarActivity.class);



    @Test
    public void changeDeadlinesShownDayTest() {
           Espresso.onView(withId(R.id.dailyButton)).perform(click());
           Espresso.onView(withId(R.id.daily_view)).check(matches(isDisplayed()));
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
