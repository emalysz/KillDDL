package com.example.emmamalysz.killddl;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

//import androidx.test.espresso.Espresso;

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
