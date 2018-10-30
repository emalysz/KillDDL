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


@RunWith(AndroidJUnit4.class)
public class SignInUITest {
    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void signInUITest() throws InterruptedException {
        Espresso.onView(withId(R.id.email)).perform(scrollTo(), replaceText("nicole@gmail.com"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.password)).perform(scrollTo(), replaceText("tester123"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.email_sign_in_button)).perform(scrollTo(), click());
        Thread.sleep(4500);
        Espresso.onView(withId(R.id.calendarView)).check(matches(isDisplayed()));
    }

    @Test
    public void invalidSignInUITest() throws InterruptedException {
        Espresso.onView(withId(R.id.email)).perform(scrollTo(), replaceText("invalidUser@gmail.com"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.password)).perform(scrollTo(), replaceText("tester123"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.email_sign_in_button)).perform(scrollTo(), click());
        Thread.sleep(4500);
        Espresso.onView(withId(R.id.login_button)).check(matches(isDisplayed()));
    }

}
