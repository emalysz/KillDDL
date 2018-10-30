package com.example.emmamalysz.killddl;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.emmamalysz.killddl.EditDeadlineActivity;
import com.example.emmamalysz.killddl.R;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;

import Controller.KillDDLController;
import Model.Deadline;
import view.DeadlineView;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(AndroidJUnit4.class)
public class DeadlineViewTests {

    KillDDLController controller = KillDDLController.getInstance();
    Deadline d = new Deadline("Exam","Itp exam",Calendar.getInstance().getTime(),1,1,0,0);
    @Rule
    public ActivityTestRule<DeadlineView> mActivityTestRule = new ActivityTestRule<DeadlineView>(DeadlineView.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(targetContext, DeadlineView.class);
            controller.addDeadline(d);
            result.putExtra("Deadline",d);
            return result;
        }
    };

    @Test
    public void markAsCompleteTest() {
        Espresso.onView(withId(R.id.completed_button)).perform(click());
        Espresso.onView(withId(R.id.daily_view)).check(matches(isDisplayed()));
        assertTrue(d.isCompleted());
    }

    @Test
    public void viewCalendarTest() {
       Espresso.onView(withId(R.id.back_button)).perform(click());
        Espresso.onView(withId(R.id.calendarView)).check(matches(isDisplayed()));
    }
    @Test
    public void editDeadlineTest() {
        Espresso.onView(withId(R.id.edit_button)).perform(click());
        Espresso.onView(withId(R.id.edit_deadline_activity)).check(matches(isDisplayed()));
    }

    @Test
    public void deleteDeadlineTest() {
        Espresso.onView(withId(R.id.delete_button)).perform(click());
        Espresso.onView(withId(R.id.daily_view)).check(matches(isDisplayed()));
    }



}
