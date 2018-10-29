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
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import java.util.Calendar;

//import androidx.test.espresso.Espresso;

import Controller.KillDDLController;
import Model.Deadline;

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

    @Test
    public void MonthDisplayedWhenCalendarClicked() {
        Espresso.onView(withId(R.id.calendarView)).perform(swipeLeft());
        Espresso.onView(withId(R.id.deadlineTitle)).check(matches(withText("This Month")));
        Espresso.onView(withId(R.id.calendarView)).perform(click());
        Espresso.onView(withId(R.id.calendarView)).perform(swipeRight());
        Espresso.onView(withId(R.id.deadlineTitle)).check(matches(withText("This Month")));
    }

    @Test
    public void dateDeadlinesDisplayedWhenDateClicked() {
        Espresso.onView(withId(R.id.calendarView)).perform(click());
        Espresso.onView(withText("This Month")).check(doesNotExist());
    }

    @Test
    public void clickToAddDeadline() {
        Espresso.onView(withId(R.id.add_button_calendar)).perform(click());
        Espresso.onView(withId(R.id.add_deadline_activity)).check(matches(isDisplayed()));
    }

    @Test
    public void testDeadlineExists() {
//        KillDDLController controller = KillDDLController.getInstance();
//        controller.addDeadline(new Deadline("Exam","itp exam",Calendar.getInstance().getTime(),
//                1, 1, 1, 1));
//        Espresso.onView(withId(R.id.calendarView)).perform(swipeLeft());
//        Espresso.onView(withId(R.id.calendarView)).perform(swipeRight());
//        Espresso.onView(withId(R.id.action_calendar)).check(matches(withText("Exam")));
    }


}
