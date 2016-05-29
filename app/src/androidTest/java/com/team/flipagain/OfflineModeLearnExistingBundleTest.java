package com.team.flipagain;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.team.flipagain.gui.login.LoginActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withResourceName;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.action.ViewActions.*;


@RunWith (AndroidJUnit4.class)

@LargeTest
public class OfflineModeLearnExistingBundleTest {

    @Rule
    public ActivityTestRule<LoginActivity> LActivityRule = new ActivityTestRule<>(LoginActivity.class);
    private LoginActivity loginActivity;

    @Before
    public void setUpActivity(){
        loginActivity = LActivityRule.getActivity();
        onView(withText("Offline")).perform(click());
    }

    @Test
    public void learnExistingBundleTest()  {
        //Button Lernen
        onView(withId(2131493039)).perform(click());
        onView(withText("Informatik")).perform(click());
        onView(withText("Informationsicherheit")).perform(click());
        onView(withText("CIA Fragen")).perform(click());
        //Button Start
        onView(withId(2131492988)).perform(click());
        onView(withText("OK")).perform(click());

        //assert first question displayed
        onView(withResourceName("TextView_question")).check(matches(isDisplayed()));
        onView(withResourceName("TextView_question")).perform(click());

        //assert solution displayed
        onView(withResourceName("TextView_Solution")).check(matches(isDisplayed()));
        onView(withResourceName("TextView_Solution")).perform(click());

        //Swiping through bundle
        for(int i = 0; i < 9; i++){
            if(i%2 > 0){
                onView(withResourceName("TextView_questionNext")).perform(swipeRight());
            }else{
                onView(withResourceName("TextView_question")).perform(swipeRight());
            }
        }

        //assert bundle closed after last question
        onView(withText("Informatik")).check(matches(isDisplayed()));
    }
}
