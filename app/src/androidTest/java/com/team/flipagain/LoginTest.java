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
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;


@RunWith (AndroidJUnit4.class)

@LargeTest
public class LoginTest {

    @Rule
    public ActivityTestRule<LoginActivity> LActivityRule = new ActivityTestRule<>(LoginActivity.class);
    private LoginActivity loginActivity;

    @Before
    public void setUpActivity(){
        //Start at CardCreatorActivity
        loginActivity = LActivityRule.getActivity();
    }

    @Test
    public void testLogin(){
        onView(withResourceName("email")).perform(typeText("bueti@hsr.ch"));
        onView(withResourceName("password")).perform(typeText("123456"));
        onView(withResourceName("email_sign_in_button")).perform(click());
        onView(withId(2131493039)).check(matches(isDisplayed()));
    }
}
