//package com.example.emmamalysz.killddl;
//
//import android.content.Context;
//import android.content.Intent;
//import android.support.test.InstrumentationRegistry;
//import android.support.test.espresso.Espresso;
//import android.support.test.espresso.remote.EspressoRemoteMessage;
//import android.support.test.rule.ActivityTestRule;
//import android.support.test.runner.AndroidJUnit4;
//
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import java.util.Calendar;
//
//import Controller.KillDDLController;
//import Model.Deadline;
//import view.DeadlineView;
//
//import static android.support.test.espresso.action.ViewActions.click;
//import static android.support.test.espresso.assertion.ViewAssertions.matches;
//import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
//import static android.support.test.espresso.matcher.ViewMatchers.withId;
//import static org.junit.Assert.assertEquals;
//
//@RunWith(AndroidJUnit4.class)
//public class DeadlineViewTest {
//
//    @Test
//    public void useAppContext() {
//        // Context of the app under test.
//        Context appContext = InstrumentationRegistry.getTargetContext();
//
//        assertEquals("com.example.emmamalysz.killddl", appContext.getPackageName());
//    }
//
////    @Rule
////    public ActivityTestRule<view.DeadlineView> deadlineViewActivityTestRule = new ActivityTestRule<DeadlineView>(view.DeadlineView.class){
////        @Override
////        protected Intent getActivityIntent(){
////            Context targetContext = InstrumentationRegistry.getTargetContext();
////            Intent result = new Intent(targetContext, DeadlineView.class);
////            Deadline d = new Deadline("Finish Project 2.4", "Test cases", Calendar.getInstance().getTime(),
////                    2, 1, 2, 0);
////            result.putExtra("deadline", d);
//////            KillDDLController controller = KillDDLController.getInstance();
//////            controller.addDeadline(d);
////            deadlineViewActivityTestRule.launchActivity(result);
////            return result;
////
////        }
////    };
////
//
//    @Test
//    public void viewCalendarTest(){
//        Espresso.onView(withId(R.id.back_button)).perform(click());
//        Espresso.onView(withId(R.id.calendarView)).check(matches(isDisplayed()));
//    }
//
//    @Test
//    public void editActivityTest() {
//        Espresso.onView(withId(R.id.edit_button)).perform(click());
//        Espresso.onView(withId(R.id.editDeadline)).check(matches(isDisplayed()));
//    }
//
//    @Test
//    public void deleteTest(){
//        Espresso.onView(withId(R.id.delete_button)).perform(click());
//        Espresso.onView(withId(R.id.daily_view)).check(matches(isDisplayed()));
//    }
//
//
//
//
//}