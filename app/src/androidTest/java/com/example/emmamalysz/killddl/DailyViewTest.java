package com.example.emmamalysz.killddl;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.remote.EspressoRemoteMessage;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

//import androidx.test.rule.
//import androidx.test.runner.AndroidJUnit4;

import view.DailyView;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class DailyViewTest {

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.emmamalysz.killddl", appContext.getPackageName());
    }

    @Rule
    public ActivityTestRule<view.DailyView> dailyViewActivityTestRule = new ActivityTestRule<>(view.DailyView.class);

    @Test
    public void addDeadlineActivityTest(){
        Espresso.onView(withId(R.id.add_button)).perform(click());
        Espresso.onView(withId(R.id.add_deadline_activity)).check(matches(isDisplayed()));
    }

    @Test
    public void monthlyViewTest() {
        Espresso.onView(withId(R.id.monthly_button)).perform(click());
        Espresso.onView(withId(R.id.calendarView)).check(matches(isDisplayed()));
    }


}
