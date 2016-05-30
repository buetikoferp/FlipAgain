package com.team.flipagain;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.team.flipagain.gui.mainScreen.cardCreator.CardCreatorActivity;
import com.team.flipagain.gui.mainScreen.cardCreator.SelectBundleActivity;

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
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith (AndroidJUnit4.class)

@LargeTest
public class DownloadBundleTest {

    @Rule
    public ActivityTestRule<SelectBundleActivity> SBActivityRule = new ActivityTestRule<>(SelectBundleActivity.class);
    private SelectBundleActivity selectBundleActivity;

    @Before
    public void setUpActivity(){
        //Start at CardCreatorActivity
        selectBundleActivity = SBActivityRule.getActivity();
    }

    @Test
    public void testDownloadBundle(){
        onView(withText("Neue karten")).perform(click());
        onView(withText("Informatik")).perform(click());
        onView(withText("Informationsicherheit")).perform(click());
    }
}
