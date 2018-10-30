package com.example.emmamalysz.killddl;

import android.content.ComponentName;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;


/**
 * Created by reysu on 10/28/18.
 */

public class SignUpUITest {
    @Rule
    public ActivityTestRule<SignupActivity> mActivityTestRule = new ActivityTestRule<>(SignupActivity.class);

    @Test
    public void signUpUITest() throws InterruptedException {
        Espresso.onView(withId(R.id.name)).perform(scrollTo(), replaceText("testUser"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.email)).perform(scrollTo(), replaceText("testUserEmmaTesting@gmail.com"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.password)).perform(scrollTo(), replaceText("tester123"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.phoneNumber)).perform(scrollTo(), replaceText("14087812704"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.button)).perform(click());
        Thread.sleep(3000);
        Espresso.onView(withId(R.id.calendarView)).check(matches(isDisplayed()));
    }

    @Test
    public void invalidSignInUITest() throws InterruptedException {
        Espresso.onView(withId(R.id.name)).perform(scrollTo(), replaceText("testUser"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.email)).perform(scrollTo(), replaceText("nicole@gmail.com"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.password)).perform(scrollTo(), replaceText("tester123"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.phoneNumber)).perform(scrollTo(), replaceText("14087812704"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.button)).perform(click());
        Thread.sleep(3000);
        Espresso.onView(withId(R.id.password)).check(matches(isDisplayed()));
    }
}
