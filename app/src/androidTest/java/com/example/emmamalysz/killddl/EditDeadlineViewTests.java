package com.example.emmamalysz.killddl;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;

import Controller.KillDDLController;
import Model.Deadline;

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


@RunWith(AndroidJUnit4.class)
public class EditDeadlineViewTests {

    @Rule
    public ActivityTestRule<EditDeadlineActivity> mActivityTestRule = new ActivityTestRule<EditDeadlineActivity>(EditDeadlineActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(targetContext, EditDeadlineActivity.class);
            KillDDLController controller = KillDDLController.getInstance();
            Deadline d = new Deadline("Exam","Itp exam",Calendar.getInstance().getTime(),1,1,0,0);
            controller.addDeadline(d);
            result.putExtra("edit",d);
            return result;
        }
    };

    @Test
    public void testDeadlineName() {
        Espresso.onView(withId(R.id.deadlineName)).check(matches(withText("Exam")));
        Espresso.onView(withId(R.id.deadlineDescription)).check(matches(withText("Itp exam")));
    }

    @Test
    public void EditDeadlineTests() {
        Espresso.onView(withId(R.id.deadlineName)).perform(scrollTo(), replaceText("Test Deadline"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.deadlineDescription)).perform(scrollTo(), replaceText("Deadline Description"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.addDeadline)).perform(scrollTo(),click());
        //  Espresso.onView(withId(R.id.daily_view)).check(matches(isDisplayed()));
    }
    @Test
    public void DeadlineTitleCannotBeBlank() {
        Espresso.onView(withId(R.id.deadlineName)).perform(scrollTo(), replaceText(""), closeSoftKeyboard());
        Espresso.onView(withId(R.id.addDeadline)).perform(scrollTo(),click());
        Espresso.onView(withId(R.id.edit_deadline_activity)).check(matches(isDisplayed()));
    }



}
